package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.web.biblio.models.Book;
import biblio.web.biblio.services.BookService;
import biblio.web.biblio.services.CopyService;

@RestController
@RequestMapping("/api/books")
public class ApiBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyService copyService;

    @GetMapping("/list")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/title/{titleId}")
    public Book getBookById(@PathVariable Long titleId) {
        return bookService.getBookById(titleId);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/title/{titleId}")
    public void deleteBook(@PathVariable Long titleId) {
        bookService.deleteBook(titleId);
    }

    // copy
    
}
