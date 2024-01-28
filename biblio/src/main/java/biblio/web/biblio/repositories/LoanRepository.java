package biblio.web.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // find loans for member by member_id
    // List<Loan> findByMemberId(Long memberId);
    List<Loan> findByMember_MemberId(Long memberId);

    List<Loan> findByCopy_CopyIdOrderByLoanDateDesc(String copyId);
    
    

}
