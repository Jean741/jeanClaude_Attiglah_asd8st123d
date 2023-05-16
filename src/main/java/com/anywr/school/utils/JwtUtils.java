package com.anywr.school.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class JwtUtils {

	private static final String SECRET_KEY = "jwtSecretKey";
	private static final long EXPIRATION_DATE = 86400;
	
	public String generateToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		List<String> authoritiesNames = new ArrayList<>();
		for(GrantedAuthority authority:authorities) {
			authoritiesNames.add(authority.getAuthority());
		}
		claims.put("authorities",authoritiesNames);
		claims.put("username", user.getUsername());
		claims.put("exp", new Date(System.currentTimeMillis()+EXPIRATION_DATE*1000));
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
	public String extractUsername(String token) {
		Claims claims =  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		String username = (String) claims.get("username");
		return username;
	}
	
	private Date extractExpirationDate(String token) {
		Claims claims =  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		log.info("Date {}",new Date((long)claims.get("exp")));
		return new Date((long)claims.get("exp")) ;
	}
	
	public boolean isTokenValid(String token, String username) {
		return !extractExpirationDate(token).before(new Date()) && username.equals(extractUsername(token));
	}
}