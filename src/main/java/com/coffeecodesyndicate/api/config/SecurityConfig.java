package com.coffeecodesyndicate.api.config;

import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        // authenticate by EMAIL (case-insensitive)
        return email -> repo.findByEmailIgnoreCase(email)
            .map(u -> User.withUsername(u.getEmail())
                .password(u.getPasswordHash()) // BCrypt hash from DB
                .accountLocked(Boolean.TRUE.equals(u.getLocked()))
                .disabled(!Boolean.TRUE.equals(u.getEnabled()))
                .roles(Boolean.TRUE.equals(u.getIsAdmin()) ? "ADMIN" : "USER")
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt
    }

    @Bean

    public AuthenticationProvider authenticationProvider(
            UserDetailsService uds, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authProvider,
            CorsConfigurationSource corsConfigurationSource) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .authenticationProvider(authProvider)
            .authorizeHttpRequests(auth -> auth
                // keep your existing public routes
                .requestMatchers("/unregistered/register").permitAll()
                .requestMatchers("/unregistered/**").permitAll()
                // add auth/register/login if you create them later
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            // ok to keep httpBasic() for now; you can remove when you switch to JWT filter
            .httpBasic(basic -> {})
            .formLogin(form -> form.disable())
            .oauth2Login(oauth2 -> {})
            .sessionManagement(sm -> sm.sessionCreationPolicy(
                org.springframework.security.config.http.SessionCreationPolicy.STATELESS
            ));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            @Value("${app.cors.allowed-origins:}") String allowedOriginsProp) {

        CorsConfiguration config = new CorsConfiguration();
        if (allowedOriginsProp != null && !allowedOriginsProp.isBlank()) {
            List<String> origins = Arrays.stream(allowedOriginsProp.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            config.setAllowedOrigins(origins);
        } else {
            config.setAllowedOrigins(List.of("*")); // dev fallback
        }
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
