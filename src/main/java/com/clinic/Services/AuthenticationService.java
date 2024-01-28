package com.clinic.Services;

import com.clinic.Models.JwtRequest;
import com.clinic.Models.JwtResponse;
import com.clinic.Payloads.UserDto;

public interface AuthenticationService {
    UserDto signup(UserDto userDto);
    JwtResponse login(JwtRequest jwtRequest);
}
