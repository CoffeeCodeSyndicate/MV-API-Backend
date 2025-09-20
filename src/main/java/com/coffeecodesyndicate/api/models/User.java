package com.coffeecodesyndicate.api.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    //isLoggedIn means they can adopt
    //if isLoggedIn is false, can view only
    private boolean isLoggedIn;

    //if true, user has admin privileges
    private boolean isAdmin;

    //a user can have many applications
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Application> applications;

    //a user can have many pets
    //no cascade or orphanRemoval, so if user is deleted the associated pets aren't deleted either and can default back to being adoptable
    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isLoggedIn() { return isLoggedIn; }
    public void setLoggedIn(boolean isLoggedIn) { this.isLoggedIn = isLoggedIn; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.isLoggedIn()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_LOGGED_IN"));
        }
        return Collections.emptyList();
    }

}
