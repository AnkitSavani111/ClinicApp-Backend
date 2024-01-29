package com.clinic.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.Models.JwtRequest;
import com.clinic.Models.JwtResponse;
import com.clinic.Payloads.UserDto;
import com.clinic.Services.AuthenticationService;


@RestController
@RequestMapping("/auth")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationService.login(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        UserDto user = authenticationService.signup(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
