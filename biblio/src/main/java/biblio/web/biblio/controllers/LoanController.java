package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import biblio.web.biblio.models.Copy;
import biblio.web.biblio.models.Loan;
import biblio.web.biblio.models.Member;
import biblio.web.biblio.services.CopyService;
import biblio.web.biblio.services.LoanService;
import biblio.web.biblio.services.MemberService;




@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CopyService copyService;

    // @GetMapping("/list")
    // public String listLoans(Model model) {
    //     List<Loan> loans = loanService.getAllLoans();
    //     model.addAttribute("loans", loans);
    //     return "loans/list";
    // }

    @GetMapping("/show/{memberId}")
    public String showLoans(@PathVariable("memberId") Long memberId, Model model) {
        // zndjdź użytkownika
        Member member = memberService.getMemberByMemberId(memberId);
        if (member == null) {
            return "redirect:/members/index";
        }
        List<Loan> loans = loanService.getMemberLoans(memberId);
        model.addAttribute("loans", loans);
        return "loans/show";
    }

    
    @PostMapping("/add/{memberId}")
    public String addLoan(@PathVariable("memberId") Long memberId, String copyId, Model model) {
        // sprawdź czy istnieje member
        Member member = memberService.findByMemberId(memberId);
        if (member == null) {
            return "redirect:/members/index";
        }
        //sprawdź czy istnieje copy
        Copy copy = copyService.getCopyById(copyId);
        if (copy == null)
        {
            model.addAttribute("error", "Podany egzemplarz nie istnieje");
            // return "redirect:/loans/show/{memberId}";
            return "redirect:/members/show/{memberId}";
        }
        // pruba stworzenia wypożyczenia
        String message = loanService.addLoan(copyId, memberId);
        model.addAttribute("message", message);
        model.addAttribute("member", member);

        // return "redirect:/loans/show/{memberId}"; // bugged strona
        return "redirect:/members/show/{memberId}";
    }

    @GetMapping("/returnes")
    public String returnes(Model model) {
        return "loans/returnes";
    }
    
    @PostMapping("/returne/{copyId}")
    public String returneLoan(@PathVariable("copyId") String copyId, Model model) {
        // pruba oddania książki
        String message = loanService.returnLoan(copyId);
        model.addAttribute("message", message);
        return "loans/returnes";
    }
    

    // Dodaj inne metody kontrolera w zależności od potrzeb
}
