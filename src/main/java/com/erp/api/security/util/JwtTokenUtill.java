package com.erp.api.security.util;

import java.security.SignatureException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.erp.api.security.entity.security.service.impl.UserDetailsImpl;
import com.erp.api.security.response.JwtResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtill {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtill.class);

	@Value("${hms.api.jwtSecret}")
	private String jwtSecret;

	@Value("${hms.api.jwtExpirationMs}")
	private int jwtExpirationMs;

	public JwtResponse generateJwtToken(Authentication authentication) {
		JwtResponse jwtResponse = new JwtResponse();

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		String token = Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		jwtResponse.setToken(token);	
		jwtResponse
				.setExpieryTime(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration());
		jwtResponse.setType("Bearer");

		return jwtResponse;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
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
