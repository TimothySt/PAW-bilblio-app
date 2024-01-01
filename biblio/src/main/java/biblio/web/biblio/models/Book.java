package biblio.web.biblio.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    private String isbn;
    private String title;
    private String description;
    private int publishedDate;
    private String publisher;
    private int pages;
    private String language;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies;

    @ManyToMany
    @JoinTable(
            name = "BooksAuthors",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;
    
    // Inne pola i metody

    public Book() {
    }

    public Book(Long titleId, String isbn, String title, String description, int publishedDate, String publisher, int pages, String language, List<Copy> copies) {
        this.titleId = titleId;
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.pages = pages;
        this.language = language;
        this.copies = copies;
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

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
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
    
}