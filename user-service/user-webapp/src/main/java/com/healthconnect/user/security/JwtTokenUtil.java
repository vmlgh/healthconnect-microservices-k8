package com.healthconnect.user.security;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.healthconnect.user.model.core.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	public static final String SCOPES = "scopes";
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 40*60*60;
    public static final String SIGNING_KEY = "aws342345rty";
	
	public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserId());
        claims.put(SCOPES, user.getRoles().stream().map(role -> "ROLE_" + role.getName().name()).collect(Collectors.toSet()));
        return Base64.getEncoder().encodeToString(Jwts.builder()
                .setClaims(claims)
                .setIssuer("https://healthconnect.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact().getBytes());
    }

}
