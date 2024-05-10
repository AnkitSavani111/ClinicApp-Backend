package com.clinic.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.Models.JwtRequest;
import com.clinic.Models.JwtResponse;
import com.clinic.Payloads.UserDto;
import com.clinic.Payloads.VerifyResponse;
import com.clinic.Services.AuthenticationService;
import com.clinic.Services.JwtService;
import com.clinic.Services.UserService;

import io.jsonwebtoken.Claims;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authenticationService.logout(response);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {
        JwtResponse jwtResponse = authenticationService.login(jwtRequest, response);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        UserDto user = authenticationService.signup(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@CookieValue("token") String token) {
        
        Claims allClaimsFromToken = jwtService.getAllClaimsFromToken(token);
        String email = allClaimsFromToken.getSubject();
        UserDto user = userService.getUserByEmail(email);
        VerifyResponse vf = new VerifyResponse(user.getUser_id(), user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(vf);
    }

}
