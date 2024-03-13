package com.clinic.Services;

import com.clinic.Models.JwtRequest;
import com.clinic.Models.JwtResponse;
import com.clinic.Payloads.UserDto;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    UserDto signup(UserDto userDto);
    JwtResponse login(JwtRequest jwtRequest, HttpServletResponse response);
}
