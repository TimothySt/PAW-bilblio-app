package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    // @Query(nativeQuery = true, name = "findBookByIsbn")// TODO naprawić
    Book findBookByIsbn(@Param("isbn") String isbn);



}
