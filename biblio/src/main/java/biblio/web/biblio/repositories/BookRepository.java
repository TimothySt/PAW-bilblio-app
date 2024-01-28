package biblio.web.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByIsbn(@Param("isbn") String isbn);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorsNameContainingIgnoreCase(String title, String authorName);

    @Query(nativeQuery = true,
    value = "SELECT DISTINCT b.* " +
            "FROM paw_biblio.books b " + // Dodaj schemat przed nazwą tabeli
            "LEFT JOIN paw_biblio.books_authors ba ON b.title_id = ba.title_id " +
            "LEFT JOIN paw_biblio.authors a ON ba.author_id = a.author_id " +
            "WHERE LOWER(unaccent(b.title)) LIKE LOWER(unaccent('%' || :searchQuery || '%')) " +
            "   OR LOWER(unaccent(a.name)) LIKE LOWER(unaccent('%' || :searchQuery || '%'))")
    List<Book> searchBooks(@Param("searchQuery") String searchQuery);
    
}
