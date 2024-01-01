package biblio.web.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Author;
import biblio.web.biblio.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    // Inne metody serwisu związane z operacjami na autorach
}
