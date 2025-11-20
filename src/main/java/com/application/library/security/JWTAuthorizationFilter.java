package com.application.library.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JWTAuthorizationFilter(final JwtService jwtService) {
		super();
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		String jwt = request.getHeader(TokenParams.HEADER_AUTHORIZATION);
		if (jwt == null || !jwt.startsWith(TokenParams.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = jwt.substring(TokenParams.TOKEN_PREFIX.length());
		final String username = jwtService.getSubject(jwt);
		if (jwtService.isValidToken(jwt)) {
			final List<String> roles = jwtService.getRoles(jwt);
			final List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			final UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
		}
		filterChain.doFilter(request, response);
	}

}
