package com.clinic.services;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    Boolean isTokenExpired(String token);
    
    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails userDetails);

    String doGenerateToken(Map<String, Object> claims, String subject);

}
