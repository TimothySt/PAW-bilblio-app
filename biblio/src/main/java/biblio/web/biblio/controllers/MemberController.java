package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblio.web.biblio.models.Member;
import biblio.web.biblio.services.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        return "members/index";
    }

    // @GetMapping("/search")
    // public String searchMembers(@RequestParam String query, Model model) {
    //     List<Member> searchResults = memberService.searchMembers(query);
    //     model.addAttribute("members", searchResults);
    //     return "members/list";
    // }

    // @GetMapping("/search")
    // public String searchMembers(@RequestParam(name = "query", required = false) String query, Model model) {
    //     if (query != null && !query.isEmpty()) {
    //         List<Member> searchResults = memberService.searchMembers(query);
    //         model.addAttribute("members", searchResults);
    //         model.addAttribute("query", query);
    //     } else {
    //         // Jeśli query jest puste, możesz obsłużyć to w odpowiedni sposób
    //         // Na przykład, przekierować użytkownika z powrotem do strony z komunikatem.
    //         // return "redirect:/members/list";
    //         return "members/list";
    //     }
    //     // List<Member> searchResults = memberService.searchMembers(query);
    //     // model.addAttribute("members", searchResults);
    //     // model.addAttribute("query", query);

    //     return "members/list";
    // }

    @GetMapping("/search")
    public String searchMembers(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<Member> searchResults = memberService.searchMembers(query);
            // wyświetlenie listy w konsoli
            for (Member member : searchResults) {
                System.out.println(member.getFirstName());
            }
            model.addAttribute("members", searchResults);
            model.addAttribute("query", query);
        } else {
            return "redirect:/members/list";
        }

        return "members/list";
    }

    @GetMapping("/list")
    public String listMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members/list";
    }

    @GetMapping("/show/{memberId}")
    public String showMember(@PathVariable("memberId")  Long memberId, Model model) {
        Member member = memberService.getMemberByMemberId(memberId);

        if (member == null) {
            // Obsługa, gdy nie ma takiego użytkownika
            return "redirect:/members/list";
        }

        model.addAttribute("member", member);
        return "members/show";
    }
}
