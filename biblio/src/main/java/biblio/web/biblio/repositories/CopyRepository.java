package biblio.web.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, String> {

    List<Copy> findByCopyId(String copyId);

    // znajdź cobie dla książki
    List<Copy> findByBookIsbn(String isbn);

}
