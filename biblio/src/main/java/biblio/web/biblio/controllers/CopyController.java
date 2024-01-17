package biblio.web.biblio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import biblio.web.biblio.models.Copy;
import biblio.web.biblio.services.CopyService;

// @RestController
@Controller
@RequestMapping("/api/copies")
public class CopyController {

    @Autowired
    private CopyService copyService;

    @GetMapping("/list")
    public String showCopyList(Model model) {
        model.addAttribute("copies", copyService.getAllCopies());
        return "copies/list";
    }

    @GetMapping("/{copyId}")
    public Copy getCopyById(@PathVariable String copyId) {
        return copyService.getCopyById(copyId);
    }

    @PostMapping
    public Copy saveCopy(@RequestBody Copy copy) {
        return copyService.saveCopy(copy);
    }

    @DeleteMapping("/{copyId}")
    public void deleteCopy(@PathVariable String copyId) {
        copyService.deleteCopy(copyId);
    }

    @PostMapping("/changeAvailability/{copyId}")
    public String changeAvailability(@PathVariable String copyId) {
        copyService.changeAvailability(copyId);
        return "redirect:/copies/list";
    }

    // Dodatkowe metody kontrolera związane z operacjami na egzemplarzach książek
}
