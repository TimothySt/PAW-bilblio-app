package biblio.web.biblio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import biblio.web.biblio.models.Member;
import biblio.web.biblio.services.MemberService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final MemberService memberService;

    @Autowired
    public RegistrationController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("member") Member member, Model model) {
        try {
            memberService.registerMember(member);
        } catch (Exception e) {
            // Przekazanie informacji o błędzie do formularza rejestracji
            model.addAttribute("error", "Wystąpił błąd podczas rejestracji. Spróbuj ponownie.: " + e);
            return "register"; // Bez przekierowania, zostaniemy na stronie rejestracji
        }
        return "redirect:/login"; // Przekierowanie na stronę logowania po udanej rejestracji
    }
}
