package biblio.web.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "copies", schema = "PAW_biblio")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_id")
    private Long copyId;

    @ManyToOne
    @JoinColumn(name = "title_id")
    @JsonIgnore
    private Book book;

    @Column(name = "available")
    private boolean available;

    // Inne pola i metody
    
    // Getters and setters

    public Long getCopyId() {
        return this.copyId;
    }

    public void setCopyId(Long copyId) {
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

}
