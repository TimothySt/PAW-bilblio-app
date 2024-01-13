package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import biblio.web.biblio.models.Author;
import biblio.web.biblio.models.Book;
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
    //     return bookService.getAllBooks();
    // }
    
    @GetMapping("/list")
    private String viewBookList(Model model)
    {
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

    // @GetMapping("/title/{titleId}")
    // public Book getBookById(@PathVariable Long titleId) {
    //     return bookService.getBookById(titleId);
    // }

    // @PostMapping
    // public Book saveBook(@RequestBody Book book) {
    //     return bookService.saveBook(book);
    // }

    // @DeleteMapping("/title/{titleId}")
    // public void deleteBook(@PathVariable Long titleId) {
    //     bookService.deleteBook(titleId);
    // }

    // copy

}
