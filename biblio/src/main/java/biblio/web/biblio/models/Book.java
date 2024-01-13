package biblio.web.biblio.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books", schema = "PAW_biblio")
@NamedNativeQuery(
    name = "findBookByIsbn",
    query = "SELECT * FROM books WHERE isbn = :isbn",
    resultClass = Book.class
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private Long titleId;

    // TODO unique
    private String isbn;
    
    private String title;
    private String description;
    private int publishedDate;
    private int pages;
    private String language;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies;

    
    @ManyToMany
    @JoinTable(
        name = "BooksAuthors",
        joinColumns = @JoinColumn(name = "title_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();
    // Inne pola i metody

    // constructors

    public Book() {
    }

    public Book(String isbn, String title, String description, int publishedDate, int pages, String language,
            Publisher publisher, List<Copy> copies, Set<Author> authors) {
        // this.titleId = titleId;
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
        this.copies = copies;
        this.authors = authors;
    }
    public Book(String isbn, String title, String description, int publishedDate, int pages, String language, Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
    }

    public Book(Long titleId, String isbn, String title, String description, int publishedDate, int pages, String language, Publisher publisher, List<Copy> copies, Set<Author> authors) {
        this.titleId = titleId;
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
        this.copies = copies;
        this.authors = authors;
    }

    
    // Getters and setters

    public Long getTitleId() {
        return this.titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(int publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Copy> getCopies() {
        return this.copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
