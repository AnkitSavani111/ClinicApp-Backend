package com.clinic.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clinic.payloads.UserDto;

public interface UserService {
    UserDetailsService userDetailsService();
    
    UserDto getUserByEmail(String email);
}
