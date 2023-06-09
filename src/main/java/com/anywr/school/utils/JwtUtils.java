package com.anywr.school.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey ;

	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;
	
	public String generateToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		List<String> authoritiesNames = new ArrayList<>();
		for(GrantedAuthority authority:authorities) {
			authoritiesNames.add(authority.getAuthority());
		}
		claims.put("authorities",authoritiesNames);
		claims.put("username", user.getUsername());
		claims.put("exp", new Date(System.currentTimeMillis()+ jwtExpiration *1000));
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
	
	public String extractUsername(String token) {
		Claims claims =  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return (String) claims.get("username");
	}
	
	private Date extractExpirationDate(String token) {
		Claims claims =  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		log.info("Date {}",new Date((long)claims.get("exp")));
		return new Date((long)claims.get("exp")) ;
	}
	
	public boolean isTokenValid(String token, String username) {
		return !extractExpirationDate(token).before(new Date()) && username.equals(extractUsername(token));
	}
}