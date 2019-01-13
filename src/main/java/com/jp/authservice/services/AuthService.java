package com.jp.authservice.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.authservice.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	private Long minutes;

	@Autowired
	private Key secretKey;

	@Autowired
	private UserService userService;

	public AuthService() {
		this.minutes = 5L;
	}

	public String login(User u) {
		User user = userService.findByEmail(u.getEmail());
		try {
			if (user != null) {
				u.setSalt(user.getSalt());
				userService.applyHashAndSalt(u);
				// TODO RETURN ERROR IF WRONG PASSWORD
				if (u.getHash().equals(user.getHash())) {
					return getToken(user);
				}
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getToken(User user) {
		String token = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256).setSubject(user.getName())
				.setExpiration(new Date(Instant.now().toEpochMilli() + getExpirationPeriodInMilliseconds())).compact();
		return token;
	}

	public Long getExpirationPeriodInMilliseconds() {
		return 60 * 1000 * minutes;
	}

	public boolean isTokenValid(String token) {
		return !isExpired(token); // && other further validations
	}

	private boolean isExpired(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration()
				.before(new Date());
	}

}
