package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	private final String privateKey = "3sWLNHJQPzaCazS8aSYyvu0VDpx2XFI65UqYPeGggG0GD7BON57itu5RYwzgPdHw_JnViLYf_A6kZlzaZOuE35v4G5TrAEHX0HTL55QTJGeWqMgsSFuyjANQR0fKSGe4dv7srYL4q2axQi1wNVRDHhxuWHI8ufP-UMoZ60jz6a-o2OtCNQ5f6Odk0jj2Ug2pLuiwwNjjIpWFIgneF9ilw4NRQB2I02-nuZFwtQgZo2bDGlD-z1OxMTkcGmPcMAbhToVWtoYlcUZs2odYbesXa2rKln4j9NedgFl36rAGIQoIeZvEqn0itJmEGXfWCJoLSbfAq5k1tSS3KwMX_swEwQ";
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		Claims body = Jwts.
                parserBuilder().
                setSigningKey(Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
	}
	
	public String getUsernameFromToken(String token) {
        Claims body = Jwts.
                parserBuilder().
                setSigningKey(Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.
            parserBuilder().
            setSigningKey(Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8)));
			return true;
		
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
