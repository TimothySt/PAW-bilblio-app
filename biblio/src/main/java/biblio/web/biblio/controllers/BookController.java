package biblio.web.biblio.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblio.web.biblio.models.Author;
import biblio.web.biblio.models.Book;
import biblio.web.biblio.models.Copy;
import biblio.web.biblio.models.Publisher;
import biblio.web.biblio.services.AuthorService;
import biblio.web.biblio.services.BookService;
import biblio.web.biblio.services.CopyService;
import biblio.web.biblio.services.PublisherService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    // @GetMapping("/list")
    // public List<Book> getAllBooks() {
    // return bookService.getAllBooks();
    // }

    @GetMapping("/list")
    private String viewBookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        // return "/books/list";
        // folder resource/templates/books/list.html
        return "books/list";

    }

    @GetMapping("/show/{isbn}")
    public String showBookDetails(@PathVariable("isbn") String isbn, Model model) {
        // Book book = bookService.getBookById(id);
        Book book = bookService.getBookByIsbn(isbn);
        List<Copy> copies = copyService.getCopiesByBookIsbn(isbn);

        model.addAttribute("book", book);
        model.addAttribute("copies", copies);
        return "books/show";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<Book> searchResults = bookService.searchBooks(query);
            model.addAttribute("books", searchResults);
            model.addAttribute("query", query);
        } else {
            // Jeśli query jest puste, możesz obsłużyć to w odpowiedni sposób
            // Na przykład, przekierować użytkownika z powrotem do strony z komunikatem.
            return "redirect:/books/list";
        }

        return "books/list";
    }

    @PostMapping("/addCopy/{isbn}")
    public String addCopyToBook(@PathVariable("isbn") String isbn, @RequestParam("copyId") String copyId) {
        copyService.addCopyToBook(isbn, copyId);
        return "redirect:/books/show/{isbn}";
    }

    @GetMapping("/create")
    public String showAddBookForm(Model model) {
        // Możesz dodać dodatkowe dane do modelu, jeśli są potrzebne w formularzu
        return "books/create";
    }

    @PostMapping("/store")
    public String addBook(Model model,
            @RequestParam("isbn") String isbn,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("published_date") String publishedDate,
            @RequestParam("pages") int pages,
            @RequestParam("language") String language,
            @RequestParam("publisherName") String publisherName,
            @RequestParam("authorNames") String authorNames) {
        Set<String> authors = new HashSet<>(Arrays.asList(authorNames.split(",")));

        try {
            bookService.addBook(isbn, title, description, LocalDate.parse(publishedDate), pages, language,
                    publisherName, authors);
            return "redirect:/books/list"; // Przekierowanie po dodaniu książki
        } catch (Exception e) {
            // Obsługa błędu (np. przekierowanie na stronę z błędem)
            model.addAttribute("error", e.getMessage());
            return "books/create";
        }
    }

    // @GetMapping("/title/{titleId}")
    // public Book getBookById(@PathVariable Long titleId) {
    // return bookService.getBookById(titleId);
    // }

    // @PostMapping
    // public Book saveBook(@RequestBody Book book) {
    // return bookService.saveBook(book);
    // }

    // @DeleteMapping("/title/{titleId}")
    // public void deleteBook(@PathVariable Long titleId) {
    // bookService.deleteBook(titleId);
    // }

    // copy

}
