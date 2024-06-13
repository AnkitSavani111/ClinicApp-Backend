package com.clinic.services.impl;

import com.clinic.dao.UserRepository;
import com.clinic.exceptions.DuplicateResourceException;
import com.clinic.models.JwtRequest;
import com.clinic.models.JwtResponse;
import com.clinic.models.User;
import com.clinic.payloads.UserDto;
import com.clinic.services.AuthenticationService;
import com.clinic.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserDto signup(UserDto userDto) {
        UserDto user = new UserDto();
        user.setUname(userDto.getUname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        user.setRole(userDto.getRole());
        Optional<User> foundUser = userRepository.findByUname(user.getUname());
        if (foundUser.isPresent()) {
            throw new DuplicateResourceException("User", "username", user.getUname());
        }

        try {
            userRepository.save(dtoToUser(user));
            return user;
        } catch (DataIntegrityViolationException e) {
            // Handle any potential data integrity violations here
            throw new RuntimeException("Error while saving user", e);
        }
    }

    public JwtResponse login(JwtRequest jwtRequest, HttpServletResponse response) {

        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        User user = userRepository.findByEmail(jwtRequest.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password"));

        String token = jwtService.generateToken(user);
        if (token != null) {
            ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(cookieExpiry)
                .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

        return new JwtResponse(token, user.getUname(), user.getUser_id());
    }

    public void logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("token", "")
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(0)
            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
