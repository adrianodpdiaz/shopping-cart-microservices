package com.adpd.auth.service;

import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
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


    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(UserDetailsDTO userDetailsDTO, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetailsDTO.getRole());
        return doGenerateToken(claims, userDetailsDTO.getEmail(), type);
    }

    private String doGenerateToken(Map<String, Object> extraClaims, String username, String type) {
        long expirationTimeLong;
        if ("ACCESS".equals(type)) {
            expirationTimeLong = Long.parseLong(tokenExpirationSeconds) * 1000;
        } else {
            expirationTimeLong = Long.parseLong(tokenExpirationSeconds) * 1000 * 5;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

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
}
