package com.rk.springbootmysql.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class JwtHelper {

    private static final String SECRET_KEY = "690a32ae642d30f0d9917669f3debb2bfc4a7ce3b706e2bd81231c4f748de315aa1bb4d868889026e7978fa2165a94e9479a27159b7c8875ce175959b988208af79bca0543ee7a65159e0a1d939892b6e0dd8791e0223a3e02e6c994392ffa51e543a4739bd6b590534b2dca83b9de73de6214efd470e7e7e7a1e11fd95aaede";
    private static final int MINUTES = 60;

    public static String generateToken(String email) {
        var now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) throws Exception {
        return getTokenBody(token).getSubject();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private static Claims getTokenBody(String token) throws Exception {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
            throw new Exception("Access denied: " + e.getMessage());
        }
    }

    private static boolean isTokenExpired(String token) throws Exception {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }
}