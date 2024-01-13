package biblio.web.biblio;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import biblio.web.biblio.repositories.AuthorRepository;
import biblio.web.biblio.repositories.BookRepository;
import biblio.web.biblio.repositories.PublisherRepository;
import biblio.web.biblio.services.BookService;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
        public void run(String... args) {
        // Dodajmy wydawców
        // Publisher publisher1 = new Publisher("Publisher 1", "Description 1");
        // Publisher publisher2 = new Publisher("Publisher 2", "Description 2");
        // publisherRepository.saveAll(List.of(publisher1, publisher2));

        // // Dodajmy autorów
        // Author author1 = new Author("Author 1", "Description 1");
        // Author author2 = new Author("Author 2", "Description 2");
        // authorRepository.saveAll(List.of(author1, author2));

        // // Dodajmy książki i powiążmy je z autorami i wydawcami
        

        Set set1 = Set.of("author1", "author2");
        Set set2 = Set.of("author2");

        //  // Dodajmy książki i powiążmy je z autorami i wydawcami
        // // Book book1 = new Book("ISBN-123", "Title 1", "Description 1", 20220101, publisher1, 200, "Language 1");
        // // Book book2 = new Book("ISBN-456", "Title 2", "Description 2", 20220102, publisher2, 250, "Language 2");

        // // book1.setAuthors(Set.of(author1, author2));
        // // book2.setAuthors(Set.of(author2));

        // // bookRepository.saveAll(List.of(book1, book2));

        // // Book book1 = new Book("ISBN-123", "Title 1", "Description 1", 20220101, publisher1, 200, "Language 1");
        // // Book book2 = new Book("ISBN-456", "Title 2", "Description 2", 20220102, publisher2, 250, "Language 2");
        // // bookRepository.saveAll(List.of(book1, book2));
        // BookService bookService = new BookService(bookRepository, publisherRepository, authorRepository);

        BookService bookService = new BookService(bookRepository, publisherRepository, authorRepository);
        try
        {
            bookService.addBook("ISBN-123", "Title 1", "Description 1", 20220101, 200, "Language 1", "Publisher 1", set1);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //  jeszcze 3 inne książki
        try
        {
            bookService.addBook("ISBN-456", "Title 2", "Description 2", 20220102, 250, "Language 2", "Publisher 2", set2);
            bookService.addBook("ISBN-789", "Title 3", "Description 3", 20220103, 300, "Language 3", "Publisher 3", set1);
            bookService.addBook("ISBN-012", "Title 4", "Description 4", 20220104, 350, "Language 4", "Publisher 4", set2);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
