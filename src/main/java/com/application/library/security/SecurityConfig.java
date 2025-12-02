package com.application.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.library.entity.RoleName;
import com.application.library.security.exception.ExceptionHandling;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Bean
	SecurityFilterChain getSecurityFilterCHain(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity.anonymous(anonymous ->anonymous.disable());
		httpSecurity.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable());

		httpSecurity.csrf(csrf -> csrf.disable());
		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		httpSecurity.authorizeHttpRequests(
				requests -> requests.requestMatchers("/login", "/api/member", "/h2-console/**").permitAll()//
						.requestMatchers(HttpMethod.GET, "/api/book").authenticated()//
						.requestMatchers("/api/book/borrow").hasAnyAuthority(RoleName.USER, RoleName.ADMIN)//
						.requestMatchers(HttpMethod.DELETE, "/api/book/**").hasAuthority(RoleName.ADMIN)//
						.requestMatchers(HttpMethod.DELETE, "/api/book/**").hasAuthority(RoleName.ADMIN)//
						.anyRequest().authenticated());

		httpSecurity.addFilter(new JWTAuthenticationFilter(jwtService, authenticationManager));
		httpSecurity.addFilterBefore(new JWTAuthorizationFilter(jwtService),
				UsernamePasswordAuthenticationFilter.class);

		httpSecurity.exceptionHandling(new ExceptionHandling());

		return httpSecurity.build();
	}
}
