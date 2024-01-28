package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.web.biblio.models.LoanStatus;

public interface LoanStatusRepository extends JpaRepository<LoanStatus, Long> {

    LoanStatus findByStatus(String status);
}
