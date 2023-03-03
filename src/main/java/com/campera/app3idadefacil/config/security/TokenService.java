package com.campera.app3idadefacil.config.security;

import com.campera.app3idadefacil.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${app3idade.jwt.expiration}")
    private String expiration;

    @Value("${app3idade.jwt.secret}")
    private String secretKey;

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public Long extractSubjectFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    public String generateToken(Authentication authentication) {
        AppUser user = (AppUser) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Campera")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
