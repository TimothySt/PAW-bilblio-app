package biblio.web.biblio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import biblio.web.biblio.services.MemberService;

@Configuration
@EnableWebSecurity // enable web security
public class SecurityConfig {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(authorize -> authorize
    // .requestMatchers("/na_razie_nic") // lista stron wymagających autoryzacji
    // .authenticated()
    // .anyRequest().permitAll())
    // .formLogin(formLogin -> formLogin
    // .loginPage("/login")
    // .permitAll()
    // .defaultSuccessUrl("/", true)
    // .failureUrl("/login?error"))
    // .logout(logout -> logout
    // .logoutUrl("/logout")
    // .permitAll()
    // .logoutSuccessUrl("/"))
    // .exceptionHandling(exceptionHandling -> exceptionHandling
    // .accessDeniedPage("/access-denied"));
    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/na_razie_nic")) // lista stron wymagających autoryzacji
                        .authenticated()
                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/access-denied"));
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(memberService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> memberService.loadUserByUsername(username);
    }
    // @Bean
    // UserDetailsService userDetailsService() {
    // return new UserDetailsService() {
    // @Override
    // public UserDetails loadUserByUsername(String username) {
    // return memberService.loadUserByUsername(username);
    // }
    // };
    // }

    // @Bean
    // UserDetailsService userDetailsService() {
    // UserDetails user = User.builder()
    // .username("user")
    // .password(passwordEncoder().encode("user"))
    // .roles("USER")
    // .build();
    // return new InMemoryUserDetailsManager(user);
    // }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

}
