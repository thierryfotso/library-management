package com.application.library.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {

	private String secretKey;
	private long jwtExpiration;

	public JwtService(@Value("${security.jwt.secret-key}") final String secretKey,
			@Value("${security.jwt.expiration-time}") final long jwtExpiration) {
		this.secretKey = secretKey;
		this.jwtExpiration = jwtExpiration;
	}

	public String generateToken(final User user) {
		final List<String> roles = new ArrayList<>();
		user.getAuthorities().forEach(g -> {
			roles.add(g.getAuthority());
		});
		final String jwt = JWT.create().withSubject(user.getUsername())
				.withArrayClaim(TokenParams.ROLES, roles.toArray(String[]::new))//
				.withIssuedAt(new Date())//
				.withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))//
				.sign(Algorithm.HMAC256(secretKey));
		return jwt;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public long getJwtExpiration() {
		return jwtExpiration;
	}

	public String getSubject(final String token) {
		final DecodedJWT decodedJWT = getDecodedJWT(token);
		return decodedJWT.getSubject();
	}

	private Map<String, Claim> getClaim(final String token) {
		final DecodedJWT decodedJWT = getDecodedJWT(token);
		return decodedJWT.getClaims();
	}

	private DecodedJWT getDecodedJWT(final String token) {
		final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
		final DecodedJWT decodedJWT = jwtVerifier.verify(token);
		return decodedJWT;
	}

	public List<String> getRoles(final String token) {
		return getClaim(token).get(TokenParams.ROLES).asList(String.class);
	}

	public boolean isValidToken(final String token) {
		return !isTokenExpired(token);
	}

	private boolean isTokenExpired(final String token) {
		return getExpirationDate(token).before(new Date());
	}

	private Date getExpirationDate(final String token) {
		final DecodedJWT decodedJWT = getDecodedJWT(token);
		return decodedJWT.getExpiresAt();
	}
}
