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

    //https://gitlab.com/Daniel.Coimbra/VET-PROJECT/-/commit/24a8db01ac4fb22f60fe5342d1fc86133445e6fe
    
    //https://gitlab.com/decode-tech/vetlab/-/blob/0c9814798563164fa126239948a8c9f3fd536dd6/src/main/java/com/decode/lucimar/VetProject/security/AuthenticationFilter.java
    
    //https://dev.to/kubadlo/spring-security-with-jwt-3j76
    
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
		if (authentication.getPrincipal() instanceof MyUserDetails) {
			MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
			byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

			String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.TOKEN_ISSUER)
					.setAudience(SecurityConstants.TOKEN_AUDIENCE).setSubject(user.getUsername())
					.setSubject(user.getPassword()).setExpiration(new Date(System.currentTimeMillis() + 864000000))
					.compact();

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print("Bearer " + token); // vai retornar o token no momento do pedido de POST para
															// confirmar se está correto
			response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token); // vai confirmar o token mas não faz o return da String no resultado do POST
		
		} else if (authentication.getPrincipal() instanceof MyClientDetails) {
			
			MyClientDetails user = (MyClientDetails) authentication.getPrincipal();
			byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

			String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.TOKEN_ISSUER)
					.setAudience(SecurityConstants.TOKEN_AUDIENCE).setSubject(user.getUsername())
					.setSubject(user.getPassword()).setExpiration(new Date(System.currentTimeMillis() + 864000000))
					.compact();
			
			response.setContentType("application/json");
			PrintWriter printer = response.getWriter();
			
			printer.print(gson.toJson(token));
			//printer.print("{\"token\": " + gson.toJson(token) + "}");
			printer.flush();
			
			response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token); // vai confirmar o token mas não faz o return da String no resultado do POST
		}

	}
}
