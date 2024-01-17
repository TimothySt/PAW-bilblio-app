package biblio.web.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // @PostMapping("/login")
    // public String login() {
    // // Obsługa logowania, np. przez Spring Security
    // return "redirect:/"; // Przekierowanie po udanym logowaniu
    // }
}
