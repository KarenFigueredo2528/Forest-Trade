package com.unbosque.edu.co.forest.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.unbosque.edu.co.forest.model.dto.UserDTO;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mi_clave_secreta";

    public String generateToken(UserDTO user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("id", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .compact();
    }
}
