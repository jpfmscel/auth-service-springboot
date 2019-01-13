package com.jp.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.authservice.entities.User;
import com.jp.authservice.services.AuthService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping(path = "/login")
	public String login(@RequestBody User user) {
		return service.login(user);
	}
}
