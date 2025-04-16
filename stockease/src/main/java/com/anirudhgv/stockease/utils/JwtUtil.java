package com.anirudhgv.stockease.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.anirudhgv.stockease.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil{

    @Value("${jwt.secret}")
    private String secretKey;
    
    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(String userId,Role role){

        byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);

        return Jwts.builder()
                .subject(userId)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key)
                .compact();
    }

    public boolean isTokenExpired(String token){
        return parseToken(token).getExpiration().before(new Date());
    }

    public String extractRole(String token){
        return parseToken(token).get("role",String.class);
    }

    public String extractUserName(String token){
        return parseToken(token).getSubject();
    }

    public boolean validateToken(String token, String userName){
        String extractedUserName = extractUserName(token);
        return (extractedUserName.equals(userName) && !isTokenExpired(token));
    }

    public Claims parseToken(String token){

        byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);

        JwtParser jwtParser = Jwts.parser()
                                .verifyWith(key)
                                .build();
        return jwtParser.parseSignedClaims(token)
                            .getPayload();
    }
    
}