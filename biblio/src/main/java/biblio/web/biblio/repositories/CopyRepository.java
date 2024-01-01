package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.web.biblio.models.Copy;

public interface CopyRepository extends JpaRepository<Copy, Long> {

    // Dodatkowe metody związane z operacjami CRUD
}
