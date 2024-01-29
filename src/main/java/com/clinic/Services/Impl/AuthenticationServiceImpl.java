package com.clinic.Services.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

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

    public UserDto signup(UserDto userDto) {
        UserDto user = new UserDto();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.DOCTOR);

        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            throw new DuplicateResourceException("User", "username", user.getUsername());
        }

        try {
            userRepository.save(dtoToUser(user));
            return user;
        } catch (DataIntegrityViolationException e) {
            // Handle any potential data integrity violations here
            throw new RuntimeException("Error while saving user", e);
        }
    }

    public JwtResponse login(JwtRequest jwtRequest) {
        logger.info("Email : {}", jwtRequest.getEmail());
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        User user = userRepository.findByEmail(jwtRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password"));
        // String token = jwtService.generateToken(user);
        return new JwtResponse(jwtService.generateToken(user), user.getEmail());
    }

    private UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
