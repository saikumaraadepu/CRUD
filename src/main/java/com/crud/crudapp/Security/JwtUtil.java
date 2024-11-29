package com.crud.crudapp.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

public class JwtUtil {

	public static final String SECRET_KEY = "secret-key";

	public static final long EXPIRATION_TIME = 864000000; //	10 Days

	public static String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}

	public static String extractUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public static boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("SignatureException: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("ExpiredJwtException: " + e.getMessage());
		}
		return false;
	}
}
