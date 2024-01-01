package biblio.web.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Copy;
import biblio.web.biblio.repositories.CopyRepository;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Copy getCopyById(Long copyId) {
        return copyRepository.findById(copyId).orElse(null);
    }

    public Copy saveCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    public void deleteCopy(Long copyId) {
        copyRepository.deleteById(copyId);
    }

    // Inne metody serwisu związane z operacjami na egzemplarzach książek
}
