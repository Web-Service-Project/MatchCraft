package com.webservice.MatchCraft.serviceimp;

import java.security.Key;
import java.sql.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService{
	
	//generate a token
	public String generateToken(UserDetails userDetails)
	{
		//generate the token responsibel to token generation
		//subject is user email
		return Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateFreshToken(Map<String, Object> extraClaims, UserDetails userDetails)
	{
		//generate the token responsibel to token generation
		//subject is user email
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//method to extract user name/email from token
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	//retruns all claims from our token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}
	
	//methodd to get user name
	//this will return the email in the particular token
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	//return a key
	//t: "our secret key" 
	//here we can map the secret key value from our proprty config
	private Key getSiginKey() {
		byte[] key = Decoders.BASE64.decode("879683269856259826590401284623862985265836598");
		return Keys.hmacShaKeyFor(key);
	}
	
	//for token validitiy
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new java.util.Date());
	}
}
