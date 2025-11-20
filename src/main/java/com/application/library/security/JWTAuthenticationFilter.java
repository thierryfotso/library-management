package com.application.library.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.library.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public JWTAuthenticationFilter(final JwtService jwtService, final AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
			throws AuthenticationException {
		try {
			final Member user = new ObjectMapper().readValue(request.getInputStream(), Member.class);
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (final IOException e) {
			throw new AuthenticationServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
		final org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
				.getPrincipal();
		final String jwt = jwtService.generateToken(user);
		response.addHeader(TokenParams.HEADER_AUTHORIZATION, jwt);
	}

	@Override
	protected void unsuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException failed) throws IOException, ServletException {
		if (failed instanceof DisabledException) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			final Map<String, Object> data = new HashMap<>();
			data.put("errorCode", "USER_DESABLED");
			data.put("message", "L'utilisateur est désactivé !");
			final ObjectMapper objectMapper = new ObjectMapper();
			final String json = objectMapper.writeValueAsString(data);
			final PrintWriter writer = response.getWriter();
			writer.println(json);
			writer.flush();
		} else {
			super.unsuccessfulAuthentication(request, response, failed);
		}
	}
}
