package biblio.web.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.LoanStatus;
import biblio.web.biblio.repositories.LoanStatusRepository;

@Service
public class LoanStatusService {
    
    @Autowired
    private LoanStatusRepository loanStatusRepository;


    public LoanStatus getLoanStatusByName(String statusName) {
        return loanStatusRepository.findByStatus(statusName);
    }

    public void saveLoanStatus(LoanStatus loanStatus) {
        loanStatusRepository.save(loanStatus);
    }
}
