package com.clinic.Services.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.Dao.UserRepository;
import com.clinic.Exceptions.DuplicateResourceException;
import com.clinic.Models.JwtRequest;
import com.clinic.Models.JwtResponse;
import com.clinic.Models.Role;
import com.clinic.Models.User;
import com.clinic.Payloads.UserDto;
import com.clinic.Services.AuthenticationService;
import com.clinic.Services.JwtService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    public UserDto signup(UserDto userDto) {
        UserDto user = new UserDto();
        user.setUname(userDto.getUname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.DOCTOR);
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

            System.out.println(user);
        // String token = jwtService.generateToken(user);
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

        return new JwtResponse(token, user.getEmail());
    }

    private User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
