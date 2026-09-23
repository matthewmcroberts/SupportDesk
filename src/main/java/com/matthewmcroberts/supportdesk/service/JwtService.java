package com.matthewmcroberts.supportdesk.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    public String generateToken(UserDetails userDetails) {
        final Date now = new Date();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .signWith(this.getSecretKey())
                .expiration(new Date(now.getTime() + expirationTime))
                .compact();
    }

    public String extractUsername(String token) {
        final Claims claims = this.getClaims(token);
        return claims.getSubject();
    }

    public boolean isValidToken(@NonNull final String token, @NonNull final UserDetails userDetails) {
        try {
            final Claims claims = this.getClaims(token);

            return claims.getSubject().equals(userDetails.getUsername()) && claims.getExpiration().after(new Date());
        } catch (final Exception e) {
            return false;
        }
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(@NonNull final String token) {
        return Jwts.parser().verifyWith(this.getSecretKey()).build().parseSignedClaims(token).getPayload();
    }
}
