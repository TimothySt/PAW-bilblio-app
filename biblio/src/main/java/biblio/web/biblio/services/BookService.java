package biblio.web.biblio.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Author;
import biblio.web.biblio.models.Book;
import biblio.web.biblio.models.Publisher;
import biblio.web.biblio.repositories.AuthorRepository;
import biblio.web.biblio.repositories.BookRepository;
import biblio.web.biblio.repositories.PublisherRepository;

@Service
public class BookService {
    //
    // @Autowired
    // BookRepository bookRepository;
    // @Autowired
    // PublisherRepository publisherRepository;
    // @Autowired
    // AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    public void addBook(String isbn, String title, String description, int publishedDate, int pages, String language, String publisherName, Set<String> authorNames) {
        // Sprawdź, czy wydawca istnieje
        Publisher publisher = publisherRepository.findByName(publisherName);
        if (publisher == null) {
            // Jeśli nie istnieje, stwórz nowego wydawcę
            publisher = new Publisher(publisherName);
            publisherRepository.save(publisher);
        }

        // Sprawdź, czy książka o danym ISBN już istnieje
        Book existingBook = bookRepository.findBookByIsbn(isbn);
        if (existingBook != null) {
            throw new RuntimeException("Książka o podanym ISBN już istnieje.");
        }

        // Tworzenie książki
        Book book = new Book(isbn, title, description, publishedDate, pages, language, publisher);

        // Dodaj połączenia między książką a autorami
        Set<Author> authors = new HashSet<>();
        for (String authorName : authorNames) {
            // Sprawdź, czy autor istnieje
            Author author = authorRepository.findByName(authorName);
            if (author == null) {
                // Jeśli nie istnieje, stwórz nowego autora
                author = new Author(authorName);
                authorRepository.save(author);
            }
            authors.add(author);
        }
        book.setAuthors(authors);

        // Zapisz książkę do bazy danych
        bookRepository.save(book);
    }

    

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
    // by isbn

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(String isbn, Book updatedBook) {
        Book existingBook = bookRepository.findBookByIsbn(isbn);

        if (existingBook != null) {
            // Zaktualizuj istniejącą książkę
            updatedBook.setTitleId(existingBook.getTitleId()); // Zachowaj istniejące id
            bookRepository.save(updatedBook);
        }
    }

    public void deleteBookByIsbn(String isbn) {
        Book existingBook = bookRepository.findBookByIsbn(isbn);

        if (existingBook != null) {
            bookRepository.delete(existingBook);
        }
    }

    // Inne metody serwisu związane z operacjami na książkach
    
}
