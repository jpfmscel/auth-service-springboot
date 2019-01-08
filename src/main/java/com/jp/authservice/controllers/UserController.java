package com.jp.authservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jp.authservice.entities.User;
import com.jp.authservice.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping
	public User insert(User u) {
		return repository.save(u);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all")
	public List<User> findAll() {
		return repository.findAll();
	}
}
