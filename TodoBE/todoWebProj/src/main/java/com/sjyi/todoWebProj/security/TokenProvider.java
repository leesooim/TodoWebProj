package com.sjyi.todoWebProj.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sjyi.todoWebProj.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KEY = Base64.getEncoder().encodeToString("FlRpX30pMqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M1Oicegkpi46AdvrcX1E6CmTUBc6IFbTPiD".getBytes());
	
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(
				Instant.now()
				.plus(1, ChronoUnit.DAYS));
		
		Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
		
		return Jwts.builder()
				.signWith(key, SignatureAlgorithm.HS512)
				.setSubject(userEntity.getId().toString()) // sub
				.setIssuer("Todo app") // iss
				.setIssuedAt(new Date()) // iat
				.setExpiration(expiryDate) // exp
				.compact();
	}
	
	public String validateAndGetUserId(String token) {
		Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY)); 
		
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
		        .build()
		        .parseClaimsJws(token)
		        .getBody();
		
		return claims.getSubject();
		}
	
}
