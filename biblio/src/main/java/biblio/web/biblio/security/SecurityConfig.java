package biblio.web.biblio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // enable web security
public class SecurityConfig {



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/na_razie_nic")// lista stron wymagających autoryzacji
        .authenticated()
        .anyRequest().permitAll()
        )
        .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("user"))
        .roles("USER")
        .build();
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
