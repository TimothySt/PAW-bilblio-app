package biblio.web.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import biblio.web.biblio.models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    Member findByEmail(String email);
    Member findByMemberId(Long memberId);

    List<Member> findByFirstNameIgnoreCase(String firstName);
    List<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelContainingIgnoreCase(String firstName, String lastName, String email, String tel);

    /* W bazie danych */
    /*
        SELECT DISTINCT m.*
        FROM paw_biblio.members m
        WHERE
            LOWER(unaccent(m.first_name)) LIKE LOWER(unaccent('%ad%'))
            OR LOWER(unaccent(m.last_name)) LIKE LOWER(unaccent('%ad%'))
            OR LOWER(unaccent(m.email)) LIKE LOWER(unaccent('%ad%'))
            OR LOWER(unaccent(m.tel)) LIKE LOWER(unaccent('%ad%'));
     */
    @Query(nativeQuery = true,
    value = "SELECT DISTINCT m.* " +
            "FROM paw_biblio.members m " +
            "WHERE LOWER(unaccent(m.first_name)) LIKE LOWER(unaccent(concat('%', :query, '%'))) " +
            "   OR LOWER(unaccent(m.last_name)) LIKE LOWER(unaccent(concat('%', :query, '%'))) " +
            "   OR LOWER(unaccent(m.email)) LIKE LOWER(unaccent(concat('%', :query, '%'))) " +
            "   OR LOWER(unaccent(m.tel)) LIKE LOWER(unaccent(concat('%', :query, '%')))")
    List<Member> searchMembersIgnoreCase(@Param("query") String query);
}
