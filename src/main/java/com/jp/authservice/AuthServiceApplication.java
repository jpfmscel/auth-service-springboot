package com.jp.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.authservice.entities.User;
import com.jp.authservice.repositories.UserRepository;

@SpringBootApplication
public class AuthServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepo.deleteAll();

		// save a couple of customers
		userRepo.save(new User("Alice", "alice@gmail.com"));
		userRepo.save(new User("Bob", "bob@gmail.com"));

		// fetch all customers
		System.out.println("Users found with findAll():");
		System.out.println("-------------------------------");
		for (User customer : userRepo.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("User found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(userRepo.findByName("Alice"));

		System.out.println("Users found with findByEmail('bob@gmail.com'):");
		System.out.println("--------------------------------");
		System.out.println(userRepo.findByEmail("bob@gmail.com"));

	}

}
