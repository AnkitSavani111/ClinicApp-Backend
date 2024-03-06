// package com.clinic.Models;

// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.Data;

// @Entity
// @Data
// public class User implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int user_id;

//     @Column(nullable = false)
//     private String username;

//     @Column(nullable = false)
//     private String email;

//     private String password;

//     @Column(nullable = false)
//     private Role role;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of(new SimpleGrantedAuthority(role.name()));
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

// }
