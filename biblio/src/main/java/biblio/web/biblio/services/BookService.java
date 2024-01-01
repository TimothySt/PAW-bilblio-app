package biblio.web.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Book;
import biblio.web.biblio.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long titleId) {
        return bookRepository.findById(titleId).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long titleId) {
        bookRepository.deleteById(titleId);
    }
    
}
