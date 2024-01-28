package biblio.web.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import biblio.web.biblio.models.Member;
import biblio.web.biblio.repositories.MemberRepository;
import biblio.web.biblio.repositories.RoleRepository;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("Użytkownik o podanym adresie email nie istnieje: " + email);
        }

        return member; // Zwracamy obiekt Member, który implementuje UserDetails
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findByMemberId(Long memberId)
    {
        return memberRepository.findByMemberId(memberId);
    }

    // by id
    public Member getMemberByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    // by id
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> searchMembers(String query) {
        // return memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelContainingIgnoreCase(query, query, query, query);
        return memberRepository.searchMembersIgnoreCase(query);
    }

    public Member saveMember(Member member) {
        // Przed zapisem do bazy danych zakoduj hasło
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public Member registerMember(Member member) {
        // sprawdzanie czy użytkownik o podanym email już istnieje
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new RuntimeException("Użytkownik o podanym adresie email już istnieje.");
        }
        // Następnie zapisz nowego użytkownika do bazy danych

        member.setPassword(passwordEncoder.encode(member.getPassword())); // Zakoduj hasło
        // Ustaw domyślną rolę dla nowego użytkownika, np. "USER"
        member.setRole(roleRepository.findByName("USER")); // Przy założeniu, że w repozytorium jest rola USER

        return memberRepository.save(member);
    }

    public String setMemberRoleAdmin(Member member)
    {
        // czy istnieje 
        Member existinagMember = findByEmail(member.getEmail());
        if (existinagMember == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("ADMIN"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na ADMIN";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }
    public String setMemberRoleAdmin(Long memberId)
    {
        Member member = findByMemberId(memberId);
        if (member == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("ADMIN"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na ADMIN";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }

    public String setMemberRoleEmployee(Member member)
    {
        // czy istnieje 
        Member existinagMember = findByEmail(member.getEmail());
        if (existinagMember == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("EMPLOYEE"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na EMPLOYEE";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }
    public String setMemberRoleEmployee(Long memberId)
    {
        Member member = findByMemberId(memberId);
        if (member == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("EMPLOYEE"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na EMPLOYEE";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }

    public String setMemberRoleUser(Member member)
    {
        // czy istnieje 
        Member existinagMember = findByEmail(member.getEmail());
        if (existinagMember == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("USER"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na USER";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }
    public String setMemberRoleUser(Long memberId)
    {
        Member member = findByMemberId(memberId);
        if (member == null) {
            return "Dane konto nie istnieje";
        }
        try {
            member.setRole(roleRepository.findByName("USER"));
            memberRepository.save(member);
            return "Pomyślnie ustawiono rolę dla " + member.getEmail() + " na USER";
        } catch (Exception e) {
            return "Nie udało się zmienić roli użytkownikowi " + member.getEmail();
        }
    }
}
