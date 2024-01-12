package biblio.web.biblio.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long publisherId;

    private String name;
    private String description;

    // Getters and setters

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
        this.description = null;
    }
    public Publisher(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Publisher(Long publisherId, String name, String description) {
        this.publisherId = publisherId;
        this.name = name;
        this.description = description;
    }

    public Long getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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

    public Publisher publisherId(Long publisherId) {
        setPublisherId(publisherId);
        return this;
    }

    public Publisher name(String name) {
        setName(name);
        return this;
    }

    public Publisher description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Publisher)) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherId, publisher.publisherId) && Objects.equals(name, publisher.name) && Objects.equals(description, publisher.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId, name, description);
    }

    @Override
    public String toString() {
        return "{" +
            " publisherId='" + getPublisherId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
