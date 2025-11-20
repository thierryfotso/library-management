package com.application.library.security.exception;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;

public class ExceptionHandling implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {

	@Override
	public void customize(final ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {
		exceptionHandlingConfigurer.authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
	}

}
