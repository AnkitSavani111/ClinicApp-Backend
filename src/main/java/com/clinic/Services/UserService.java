package com.clinic.Services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clinic.Payloads.UserDto;

public interface UserService {
    UserDetailsService userDetailsService();
    
    UserDto getUserByEmail(String email);
}
