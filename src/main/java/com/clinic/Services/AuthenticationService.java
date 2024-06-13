package com.clinic.services;

import com.clinic.models.JwtRequest;
import com.clinic.models.JwtResponse;
import com.clinic.payloads.UserDto;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    UserDto signup(UserDto userDto);
    JwtResponse login(JwtRequest jwtRequest, HttpServletResponse response);
    void logout(HttpServletResponse response);
}
