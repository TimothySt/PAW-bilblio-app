package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.web.biblio.models.Copy;
import biblio.web.biblio.services.CopyService;

@RestController
@RequestMapping("/api/copies")
public class CopyController {

    @Autowired
    private CopyService copyService;

    @GetMapping
    public List<Copy> getAllCopies() {
        return copyService.getAllCopies();
    }

    @GetMapping("/{copyId}")
    public Copy getCopyById(@PathVariable Long copyId) {
        return copyService.getCopyById(copyId);
    }

    @PostMapping
    public Copy saveCopy(@RequestBody Copy copy) {
        return copyService.saveCopy(copy);
    }

    @DeleteMapping("/{copyId}")
    public void deleteCopy(@PathVariable Long copyId) {
        copyService.deleteCopy(copyId);
    }

    // Dodatkowe metody kontrolera związane z operacjami na egzemplarzach książek
}
