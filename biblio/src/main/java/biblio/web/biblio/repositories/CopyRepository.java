package biblio.web.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.web.biblio.models.Copy;


public interface CopyRepository extends JpaRepository<Copy, Long>
{

    List<Copy> findByCopyId(String copyId);// TODO ?

    // Dodatkowe metody związane z operacjami CRUD
}
