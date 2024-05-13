package com.adpd.auth.service;

import com.adpd.auth.resource.dto.UserDetailsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.seconds}")
    private String tokenExpirationSeconds;


    public String generate(UserDetailsDTO userDetailsDTO, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetailsDTO.getRole());
        return doGenerateToken(claims, userDetailsDTO.getEmail(), type);
    }

    public Date getTokenExpiration(String token) {
        return this.getAllClaimsFromToken(token).getExpiration();
    }

    private String doGenerateToken(Map<String, Object> extraClaims, String username, String type) {
        long expirationTime;
        if ("ACCESS".equals(type)) {
            expirationTime = Long.parseLong(tokenExpirationSeconds) * 1000;
        } else {
            expirationTime = Long.parseLong(tokenExpirationSeconds) * 1000 * 5;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTime);

        return Jwts
                .builder()
                .claims().add(extraClaims)
                .subject(username)
                .issuedAt(createdDate)
                .expiration(expirationDate)
                .and()
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
