package biblio.web.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BasicController {

    // @GetMapping("/")
    // public String index(Model model) {
    // model.addAttribute("appUrl", environment.getProperty("app.url"));
    // return "index";
    // }

    // @GetMapping("/")
    // public String index(Model model) {
    // // dodaj do modelu zalogowane dane
    // model.addAttribute("user",
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    // // model.addAttribute("appUrl", environment.getProperty("app.url"));
    // return "index";
    // }

}
