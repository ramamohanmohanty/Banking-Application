package org.jt.BankingManagementSystem.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jt.BankingManagementSystem.service.IJWTService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
@Service
public class JWTServiceImpl implements IJWTService {
    private final String secret = "847688b1c04f8340320fc73d154469b7bfd47e9a4dd23a180d3b265163ad23de";
    @Override
    public String generateToken(String accountNumber) {
        return Jwts.builder()
                .signWith(getKey())
                .subject(accountNumber)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    private SecretKey getKey() {
        byte bytes[] = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }
}
