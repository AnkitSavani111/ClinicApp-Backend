// package com.clinic.Services.Impl;

// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import javax.crypto.SecretKey;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// import com.clinic.Services.JwtService;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;

// @Service
// public class JwtServiceImpl implements JwtService{
//     // requirement :
//     private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 60 * 60;

//     // public static final long JWT_TOKEN_VALIDITY = 60;
//     private static String secret = "qFdyhjErunLhLiugMkjhKJBKJjkbjafacasDasfasxASFAxASkFACASDFASFASfDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

//     private static SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

//     // retrieve username from jwt token
//     public String getUsernameFromToken(String token) {
//         return getClaimFromToken(token, Claims::getSubject);
//     }

//     // retrieve expiration date from jwt token
//     public Date getExpirationDateFromToken(String token) {
//         return getClaimFromToken(token, Claims::getExpiration);
//     }

//     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = getAllClaimsFromToken(token);
//         return claimsResolver.apply(claims);
//     }

//     // for retrieveing any information from token we will need the secret key
//     public Claims getAllClaimsFromToken(String token) {
//         return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
//     }

//     // check if the token has expired
//     public Boolean isTokenExpired(String token) {
//         final Date expiration = getExpirationDateFromToken(token);
//         return expiration.before(new Date());
//     }

//     // generate token for user
//     public String generateToken(UserDetails userDetails) {
//         Map<String, Object> claims = new HashMap<>();
//         return doGenerateToken(claims, userDetails.getUsername());
//     }

//     // while creating the token -
//     // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
//     // 2. Sign the JWT using the HS512 algorithm and secret key.
//     // 3. According to JWS Compact
//     // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//     // compaction of the JWT to a URL-safe string
//     public String doGenerateToken(Map<String, Object> claims, String subject) {

//         return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
//                 .expiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000)))
//                 .signWith(secretKey).compact();
//     }

//     // validate token
//     public Boolean validateToken(String token, UserDetails userDetails) {
//         final String username = getUsernameFromToken(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

// }
