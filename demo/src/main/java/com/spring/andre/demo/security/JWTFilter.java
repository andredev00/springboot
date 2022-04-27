package com.spring.andre.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring.andre.demo.service.MyUserDetailsService;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	// it will validate every request, if is present a token in the header
	// parameters, and if it is, it will validate the token information
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			if (jwt == null || jwt.isBlank()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token in Bearer Header");
			} else {
				try {
					String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
					UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,
							userDetails.getPassword(), userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JWTVerificationException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
				}
			}
		}
		if (authHeader == null && !request.getRequestURL().toString().contains("v2/api-docs")
				&& !request.getRequestURL().toString().contains("swagger")
				&& !request.getRequestURL().toString().contains("/h2-console")
				&& !request.getRequestURL().toString().contains("/sign-up")
				&& !request.getRequestURL().toString().contains("/login")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
