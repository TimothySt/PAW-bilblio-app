package biblio.web.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.web.biblio.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
