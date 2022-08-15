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
				&& !request.getRequestURL().toString().contains("/login")
				&& !request.getRequestURL().toString().contains("/allHomes")
				&& !request.getRequestURL().toString().contains("register")
				&& !request.getRequestURL().toString().contains("/uploadFile")
				&& !request.getRequestURL().toString().contains("/deleteFile")
				&& !request.getRequestURL().toString().contains("/getFile")
				&& !request.getRequestURL().toString().contains("/findHome")
				&& !request.getRequestURL().toString().contains("/email")
				&& !request.getRequestURL().toString().contains("/agents")
				&& !request.getRequestURL().toString().contains("/edit")
				&& !request.getRequestURL().toString().contains("/reset")
				&& !request.getRequestURL().toString().contains("/active")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
		} else {
		 	response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
	        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
	        response.addHeader("Access-Control-Allow-Credentials", "true");
	        response.addIntHeader("Access-Control-Max-Age", 10);
			filterChain.doFilter(request, response);
		}
	}

}
