package com.example.bank.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final long expiration;
    private final SecretKey key;

    public JwtUtils(
            @Value("${jwt.expiration}") long expiration,
            @Value("${jwt.secret}") String secret
    ) {
        System.out.println(expiration);
        this.expiration = expiration;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // generate-token
    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .subject(username)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    // parse-token
    public String parseToken(String token) {
        String secret = "qkdoqjbvjspfpekfnlfaldocfubwhelqkenfgakqjehudbsfsjdhf29404017303rhsdkkh87284949201009284ubdjskd123";
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims payload = (Claims) Jwts.parser() //Object가 뜨니까 강제 형변환 해서 긁어오기
                .verifyWith(key)
                .build()
                .parse(token)
                .getPayload();
        return payload.getSubject();
    }
}
