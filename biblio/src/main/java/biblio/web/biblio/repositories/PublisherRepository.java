package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>
{

    Publisher findByName(String name);

    // Inne metody repozytorium związane z wydawcami

}
