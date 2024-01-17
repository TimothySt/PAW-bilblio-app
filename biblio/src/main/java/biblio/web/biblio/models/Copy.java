package biblio.web.biblio.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Copies")
public class Copy {

    @Id
    @Column(name = "copy_id")
    private String copyId;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Book book;

    private boolean available;

    // Getters and setters

    public Copy() {
    }

    public Copy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
        this.available = true;
    }

    public Copy(String copyId, Book book, boolean available) {
        this.copyId = copyId;
        this.book = book;
        this.available = available;
    }

    public String getCopyId() {
        return this.copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Copy copyId(String copyId) {
        setCopyId(copyId);
        return this;
    }

    public Copy book(Book book) {
        setBook(book);
        return this;
    }

    public Copy available(boolean available) {
        setAvailable(available);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Copy)) {
            return false;
        }
        Copy copy = (Copy) o;
        return Objects.equals(copyId, copy.copyId) && Objects.equals(book, copy.book) && available == copy.available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, book, available);
    }

    @Override
    public String toString() {
        return "{" +
                " copyId='" + getCopyId() + "'" +
                ", book='" + getBook() + "'" +
                ", available='" + isAvailable() + "'" +
                "}";
    }

}
