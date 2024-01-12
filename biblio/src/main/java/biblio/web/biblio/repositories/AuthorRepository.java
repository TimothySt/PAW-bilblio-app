package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.web.biblio.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Dodatkowe metody związane z operacjami CRUD
    Author findByName(String name);

}
