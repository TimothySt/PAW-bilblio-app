package biblio.web.biblio.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Copy;
import biblio.web.biblio.models.Loan;
import biblio.web.biblio.models.LoanStatus;
import biblio.web.biblio.models.Member;
import biblio.web.biblio.repositories.LoanRepository;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private LoanStatusService loanStatusService;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getMemberLoans(Long memberId) {
        return loanRepository.findByMember_MemberId(memberId);
    }

    public Loan getLoanById(Long loanId) {
        return loanRepository.findById(loanId).orElse(null);
    }

    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }

    
    public String addLoan(String copyId, Long memberId)
    {
        // znajdź member dla id
        Member member = memberService.getMemberByMemberId(memberId);
        // czy istnieje użytkownik
        if (member == null)//
        {
            // nie istnieje
            return("Nie ma takiego użytkownika");
        }
        // znajdź copy dla id
        Copy copy = copyService.getCopyById(copyId);
        // czy istnieje copy
        if (copy == null)//
        {
            // nie istnieje
            return("Nie ma takiego egzemplarza");
        }
        // czy copy jest dostępny
        if (!copy.isAvailable())//
        {
            // nie jest dostępny
            return("Egzemplarz jest niedostępny");
        }
        // znajdź loan status borrowed
        LoanStatus loanStatus = loanStatusService.getLoanStatusByName("borrowed");
        if (loanStatus == null) {
            // nie istnieje
            return("Nie ma potrzebnego statusu wypożyczenia");
        }
        // ustaw okres wypożyczenia
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusDays(30);
        // utwórz loan
        Loan loan = new Loan(copy, member, currentDate, dueDate, loanStatus);
        loanRepository.save(loan);
        // ustaw copy jako niedostępny
        copy.setAvailable(false);
        copyService.saveCopy(copy);

        return "Pomyślnie wypożyczono książkę "+copy.getCopyId();
    }
    public Loan findLatestLoanByCopyId(String copyId) {
        List<Loan> loans = loanRepository.findByCopy_CopyIdOrderByLoanDateDesc(copyId);
        return loans.isEmpty() ? null : loans.get(0);
    }
    // // zwrót wypożyczenia
    public String returnLoan(String copyId) {
        // znajdź copię książki
        Copy copy = copyService.getCopyById(copyId);
        // czy istnieje copy
        if (copy == null)//
        {
            // nie istnieje
            return ("Nie ma takiego egzemplarza");
        }
        // czy copy jest niedostępny
        if (copy.isAvailable())//
        {
            // jest dostępny
            return ("Egzemplarz nie jest wypożyczony");
        }
        // znajdź loan dla copy
        Loan loan = findLatestLoanByCopyId(copyId);
        // czy istnieje loan
        if (loan == null)//
        {
            // nie istnieje
            return ("Nie ma takiego dla tego egzemplarza wypożyczenia");
        }
        // sprawdź czy nie jest zrócona
        if (loan.getLoanStatus().getStatus().equals("returned"))//
        {
            // jest zwrócona
            return ("Egzemplarz jest już zwrócony");
        }
        // ustaw status wypożyczenia na zwrócone
        LoanStatus loanStatus = loanStatusService.getLoanStatusByName("returned");
        if (loanStatus == null) {
            // nie istnieje
            return ("Nie ma potrzebnego statusu wypożyczenia");
            /* OR trwow runtime exception*/
            // throw new RuntimeException("Nie ma potrzebnego statusu wypożyczenia");

        }
        loan.setLoanStatus(loanStatus);
        // zapisz loan
        loanRepository.save(loan);

        // ustaw copy jako dostępny
        copy.setAvailable(true);
        copyService.saveCopy(copy);

        return "Pomyślnie dokonano zwrotu dla " + copy.getCopyId();
    }
    // przedłużenie
    public String exctendLoan(String copyId)
    {
        // znajdź copię książki
        Copy copy = copyService.getCopyById(copyId);
        // czy istnieje copy
        if (copy == null)//
        {
            // nie istnieje
            return ("Nie ma takiego egzemplarza");
        }
        // czy copy jest niedostępny
        if (copy.isAvailable())//
        {
            // jest dostępny
            return ("Egzemplarz nie jest wypożyczony");
        }
        // znajdź loan dla copy
        Loan loan = findLatestLoanByCopyId(copyId);
        // czy istnieje loan
        if (loan == null)//
        {
            // nie istnieje
            return ("Nie ma takiego dla tego egzemplarza wypożyczenia");
        }
        // sprawdź czy nie jest zrócona
        if (loan.getLoanStatus().getStatus().equals("returned"))//
        {
            // jest zwrócona
            return ("Egzemplarz jest już zwrócony");
        }
        // sprawdzenie czy nie przedłużono już do 90 dni
        LocalDate loanDate = loan.getLoanDate();
        LocalDate dueDate = loan.getDueDate();
        // jeśli między loan date and due date jest 89 dni to nie można przedłużyć
        if (loanDate.plusDays(89).isBefore(dueDate) || loanDate.plusDays(89).isEqual(dueDate))//
        {
            // nie można przedłużyć
            return ("Nie można bardziej przedłużyć wypożyczenia. Wypożyczono już na 90 dni.");
        }
        // ustaw nowy due date
        LocalDate newDueDate = dueDate.plusDays(30);
        loan.setDueDate(newDueDate);
        // zapisz loan
        // loanRepository.saveAndFlush(loan);
        loanRepository.save(loan);
        // potwierdzenie
        return "Pomyślnie przedłużono wypożyczenie dla " + copy.getCopyId()+", do "+loan.getDueDate();
    }
}
