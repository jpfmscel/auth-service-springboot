package com.jp.authservice.configuration;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.jsonwebtoken.security.Keys;

@Configuration
public class SystemVariablesProducer {

	private final static String KEY = "key.secret";

	@Autowired
	private Environment env;

	@Bean
	public Key getSecretKey() {
		return Keys.hmacShaKeyFor(env.getProperty(KEY).getBytes());
	}
}
