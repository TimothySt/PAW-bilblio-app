package biblio.web.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import biblio.web.biblio.models.Author;
import biblio.web.biblio.services.AuthorService;

// @RestController
@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }

    // Dodatkowe metody kontrolera związane z operacjami na autorach
}
