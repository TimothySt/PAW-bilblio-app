package biblio.web.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Book;
import biblio.web.biblio.models.Copy;
import biblio.web.biblio.repositories.BookRepository;
import biblio.web.biblio.repositories.CopyRepository;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Copy getCopyById(String copyId) {
        return copyRepository.findById(copyId).orElse(null);
    }

    public Copy saveCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    public void updateCopy(Copy copy) {
        copyRepository.save(copy);
    }

    public void deleteCopy(String copyId) {
        copyRepository.deleteById(copyId);
    }

    public List<Copy> getCopiesByBookIsbn(String isbn) {
        // Implementacja pobierania kopii dla danej książki
        // Możesz użyć copyRepository.findByBookIsbn(isbn) lub podobnej metody
        // Zwróć listę kopii

        // znajdź wszystkie kompie gdzie bookId z isbn
        return copyRepository.findByBookIsbn(isbn);
    }

    public void addCopyToBook(String isbn, String copyId) {
        // Implementacja dodawania kopii do danej książki
        // Utwórz nowy obiekt Copy, ustawiając copyId, książkę i dostępność
        // Zapisz kopię do bazy danych

        // znajdź książkę po isbn
        Book book = bookRepository.findBookByIsbn(isbn);
        // utwórz nowy obiekt Copy
        Copy copy = new Copy(copyId, book);

        // zapisz kopię do bazy danych
        copyRepository.save(copy);
    }

    public void changeAvailability(String copyId, boolean newAvailability) {
        Copy copy = getCopyById(copyId);

        if (copy != null) {
            copy.setAvailable(newAvailability);
            updateCopy(copy);
        }
    }

    public void changeAvailability(String copyId) {
        Copy copy = getCopyById(copyId);

        if (copy != null) {
            copy.setAvailable(!copy.getAvailable());
            updateCopy(copy);
        }
    }

    // Inne metody serwisu związane z operacjami na egzemplarzach książek
}
