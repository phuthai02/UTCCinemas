package utc.cinemas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import utc.cinemas.config.jwt.JwtAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final List<String> PUBLIC_URLS = List.of(
            "/css/**",
            "/image/**",
            "/javascript/**",
            "/utc-cinemas/login",
            "/api/auth/**"
    );

    private static final List<String> ADMIN_PROTECTED_URLS = List.of(
            "/utc-cinemas/home",
            "/utc-cinemas/cinemas/**",
            "/utc-cinemas/rooms/**",
            "/utc-cinemas/seats/**",
            "/utc-cinemas/users/**",
            "/utc-cinemas/movies/**",
            "/utc-cinemas/showtimes/**",
            "/utc-cinemas/tickets",
            "/utc-cinemas/reports",
            "/api/admin/**"
    );

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    PUBLIC_URLS.forEach(url -> auth.requestMatchers(url).permitAll());
                    ADMIN_PROTECTED_URLS.forEach(url -> auth.requestMatchers(url).hasAuthority("ROLE_ADMIN"));
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/utc-cinemas/login")
                        .defaultSuccessUrl("/utc-cinemas/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/utc-cinemas/logout"))
                        .logoutSuccessUrl("/utc-cinemas/login")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .invalidSessionUrl("/utc-cinemas/login")
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/utc-cinemas/access-denied")
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}