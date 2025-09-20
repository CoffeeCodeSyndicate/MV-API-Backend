package com.coffeecodesyndicate.api.config;

import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic();

        return http.build();
    }

    // This bean creates the PasswordEncoder instance that UserService needs.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This bean tells Spring Security how to load a user from the database.
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.coffeecodesyndicate.api.models.User user = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            String role = "Role_Viewer"; //create initial role
            if (user.getIsLoggedIn()) {
                role = "Role_LoggedIn";
            }
            if (user.getIsAdmin()) {
                role = "Role_Admin";
            }

            String finalRole = role; //set what the determined role really is
            //and now return everything we need for the user
            return new UserDetails() {
                @Override public String getUsername() { return user.getUsername(); }
                @Override public String getPassword() { return user.getPassword(); }
                @Override
                public java.util.Collection<org.springframework.security.core.GrantedAuthority> getAuthorities() {
                    return Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(finalRole));
                }
                @Override public boolean isAccountNonExpired() { return true; }
                @Override public boolean isAccountNonLocked() { return true; }
                @Override public boolean isCredentialsNonExpired() { return true; }
                @Override public boolean isEnabled() { return true; }
            };
        };
    }
}
