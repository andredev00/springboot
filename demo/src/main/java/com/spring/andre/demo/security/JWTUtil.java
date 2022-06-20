package com.spring.andre.demo.security;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTUtil {
	
	@Value("${jwt_secret}")
	private String secret;
	
	//this method will be responsive for generating our app token
	public String generateToken(String email, String permissions) {
		return JWT.create()
				.withSubject("User Details")
				.withClaim("email", email)
				.withClaim("permissions", permissions)
				.withIssuedAt(new Date())
				.withIssuer("Demo App")
				.sign(Algorithm.HMAC256(secret));
	}
	
	//this method will be responsive for validating and retrieve information for our app token
	public String validateTokenAndRetrieveSubject(String token) {
		JWTVerifier verifier = JWT
				.require(Algorithm.HMAC256(secret))
				.withSubject("User Details")
				.withIssuer("Demo App")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("email").asString();
	}
}
