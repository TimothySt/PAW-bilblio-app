package biblio.web.biblio;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import biblio.web.biblio.models.LoanStatus;
import biblio.web.biblio.models.Member;
import biblio.web.biblio.models.Role;
import biblio.web.biblio.repositories.AuthorRepository;
import biblio.web.biblio.repositories.BookRepository;
import biblio.web.biblio.repositories.LoanStatusRepository;
import biblio.web.biblio.repositories.MemberRepository;
import biblio.web.biblio.repositories.PublisherRepository;
import biblio.web.biblio.repositories.RoleRepository;
import biblio.web.biblio.services.BookService;
import biblio.web.biblio.services.MemberService;

@Component
public class DataLoader implements CommandLineRunner {

    //autowire service
    @Autowired
    private MemberService memberService;
    // autowire respository
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final RoleRepository roleRepository;
    private final LoanStatusRepository loanStatusRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository, AuthorRepository authorRepository,
            PublisherRepository publisherRepository, RoleRepository roleRepository,
            LoanStatusRepository loanStatusRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.roleRepository = roleRepository;
        this.loanStatusRepository = loanStatusRepository;
        this.memberRepository = memberRepository;
    }

    private void createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }
    }

    private void createStatusIfNotFound(String status)
    {
        LoanStatus loanStatus = loanStatusRepository.findByStatus(status);
        if (loanStatus == null) {
            loanStatus = new LoanStatus(status);
            loanStatusRepository.save(loanStatus);
        }
    }
    private void createStatusIfNotFound(Long id, String status)
    {
        LoanStatus loanStatus = loanStatusRepository.findByStatus(status);
        if (loanStatus == null) {
            loanStatus = new LoanStatus(id, status);
            loanStatusRepository.save(loanStatus);
        }
    }
    
    private Member createBasicMemberIfNotFound(String firstaName, String lastName, String email, String tel,String address, String password)
    {
        // TODO
        Member member = memberService.findByEmail(email);
        if (member == null) {
            member = new Member(firstaName, lastName, email, tel, address, password);
            memberService.registerMember(member);
            return member;
        }
        else
            return null;
    }

    @Override
    public void run(String... args) {
        // Dodaj role przy uruchamianiu aplikacji, jeśli nie istnieją
        createRoleIfNotFound("USER");
        createRoleIfNotFound("EMPLOYEE");
        createRoleIfNotFound("ADMIN");

        // stworzenie bazowych kont
        Member admin = createBasicMemberIfNotFound("Admin", "Admin", "admin@admin","999999999" ,"admin address","admin");
        if (admin != null)
        {
            memberService.setMemberRoleAdmin(admin);
        }
        Member employee = createBasicMemberIfNotFound("Employee", "Employee", "employee@employee", "888888888",
                "employee address", "employee");
        if (employee != null)
        {
            memberService.setMemberRoleEmployee(employee);
        }
        Member user = createBasicMemberIfNotFound("User", "User", "user@user", "777777777", "user address", "user");
        if (user != null) {
            memberService.setMemberRoleUser(user);
        }
        // dodaj loan status
        /*
            "returned"
            "borrowed"
            "reserved"
            "canceled"
            "expired"
            "overdue"
            "lost"
            "damaged"
            "pending"
        */
        createStatusIfNotFound(1L,"returned");
        createStatusIfNotFound(2L,"borrowed");
        createStatusIfNotFound(3L,"reserved");
        createStatusIfNotFound(4L,"canceled");
        createStatusIfNotFound(5L,"expired");
        createStatusIfNotFound(6L,"overdue");
        createStatusIfNotFound(7L,"lost");
        createStatusIfNotFound(8L,"damaged");
        createStatusIfNotFound(9L,"pending");


        // jakieś książki
        Set set1 = Set.of("author1", "author2");
        Set set2 = Set.of("author2");

        BookService bookService = new BookService(bookRepository, publisherRepository, authorRepository);
        try
        {
            bookService.addBook("ISBN-123", "Title 1", "Description 1", LocalDate.parse("2022-01-01"), 200,
                    "Language 1", "Publisher 1", set1);
        }
        catch (Exception e) {
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
