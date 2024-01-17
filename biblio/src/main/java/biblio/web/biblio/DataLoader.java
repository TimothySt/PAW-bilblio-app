package biblio.web.biblio;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import biblio.web.biblio.models.Role;
import biblio.web.biblio.repositories.AuthorRepository;
import biblio.web.biblio.repositories.BookRepository;
import biblio.web.biblio.repositories.PublisherRepository;
import biblio.web.biblio.repositories.RoleRepository;
import biblio.web.biblio.services.BookService;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository, AuthorRepository authorRepository,
            PublisherRepository publisherRepository, RoleRepository roleRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.roleRepository = roleRepository;
    }

    private void createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }
    }

    @Override
    public void run(String... args) {
        // Dodaj role przy uruchamianiu aplikacji, jeśli nie istnieją
        createRoleIfNotFound("USER");
        createRoleIfNotFound("EMPLOYEE");
        createRoleIfNotFound("ADMIN");

        // jakieś książki
        Set set1 = Set.of("author1", "author2");
        Set set2 = Set.of("author2");

        BookService bookService = new BookService(bookRepository, publisherRepository, authorRepository);
        try {
            bookService.addBook("ISBN-123", "Title 1", "Description 1", LocalDate.parse("2022-01-01"), 200,
                    "Language 1", "Publisher 1", set1);
        } catch (Exception e) {
            System.out.println(e);
        }
        // jeszcze 3 inne książki
        try {
            bookService.addBook("ISBN-456", "Title 2", "Description 2", LocalDate.parse("2022-01-02"), 250,
                    "Language 2", "Publisher 2", set2);
            bookService.addBook("ISBN-789", "Title 3", "Description 3", LocalDate.parse("2022-01-03"), 300,
                    "Language 3", "Publisher 3", set1);
            bookService.addBook("ISBN-012", "Title 4", "Description 4", LocalDate.parse("2022-01-04"), 350,
                    "Language 4", "Publisher 4", set2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
