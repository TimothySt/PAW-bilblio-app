package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Dodatkowe metody związane z operacjami CRUD
    Author findByName(String name);

}
