package biblio.web.biblio.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors", schema = "PAW_biblio")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
    
    // constructors

    public Author(Long authorId, String name, String description, Set<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.description = description;
        this.books = books;
    }

    public Author(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Author(String name) {
        this.name = name;
        this.description = null;
    }

    public Author() {
    }


    // Getters and setters

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
