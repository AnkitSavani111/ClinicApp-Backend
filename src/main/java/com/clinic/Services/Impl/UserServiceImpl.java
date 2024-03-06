// package com.clinic.Services.Impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.clinic.Dao.UserRepository;
// import com.clinic.Services.UserService;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class UserServiceImpl implements UserService {

//     @Autowired
//     private UserRepository userRepository;

//     public UserDetailsService userDetailsService() {
//         return new UserDetailsService() {

//             @Override
//             public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                 return userRepository.findByEmail(email)
//                         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//             }
//         };
//     }
// }
