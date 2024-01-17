package biblio.web.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // return member; // Zwracamy obiekt Member, który implementuje UserDetails
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().getName()) // Przekazuje role użytkownika
                .build();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
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
}
