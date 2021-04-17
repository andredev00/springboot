package com.spring.andre.demo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.andre.demo.service.MyClientDetails;
import com.spring.andre.demo.service.MyUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }
    
    Gson gson = new Gson();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    	com.spring.andre.demo.model.User user = parseUser(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }
   
    private com.spring.andre.demo.model.User parseUser(HttpServletRequest request) {
    	try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(request.getInputStream(), com.spring.andre.demo.model.User.class);
		} catch (IOException exception) {
			// Return empty "invalid" login data
			return new com.spring.andre.demo.model.User();
		}		
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws IOException {
		if (authentication.getPrincipal() instanceof MyClientDetails) {
			MyClientDetails user = (MyClientDetails) authentication.getPrincipal();
			
			byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

			String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.TOKEN_ISSUER)
					.setAudience(SecurityConstants.TOKEN_AUDIENCE).setSubject(user.getUsername())
					.setSubject(user.getPassword()).setSubject(user.getName()).setExpiration(new Date(System.currentTimeMillis() + 864000000))
					.compact();
			
			response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);

			response.setContentType("application/json");
			PrintWriter printer = response.getWriter();
			printer.print("{\"token\": " + gson.toJson(token) + "}");
			printer.flush();

		
		} else if (authentication.getPrincipal() instanceof MyUserDetails) {
			
			MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
			byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

			String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.TOKEN_ISSUER)
					.setAudience(SecurityConstants.TOKEN_AUDIENCE).setSubject(user.getUsername())
					.setSubject(user.getPassword()).setSubject(user.getName()).setExpiration(new Date(System.currentTimeMillis() + 864000000))
					.compact();
			
			response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
			
			response.setContentType("application/json");
			PrintWriter printer = response.getWriter();
			printer.print("{\"token\": " + gson.toJson(token) + "}");
			printer.flush();
			
			
		}

	}
}
