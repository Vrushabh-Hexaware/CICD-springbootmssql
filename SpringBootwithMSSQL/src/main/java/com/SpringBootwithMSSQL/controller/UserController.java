package com.SpringBootwithMSSQL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootwithMSSQL.entity.User;
import com.SpringBootwithMSSQL.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public List<User> fetchAll() {
		return this.userService.fetchAll();
	}

	@GetMapping("/user/{id}")
	public User fetchById(@PathVariable Long id) {
		return this.userService.fetchById(id);
	}

	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable Long id) {
		this.userService.delete(id);
	}

	@PostMapping("/user")
	public User create(@RequestBody User user) {
		return this.userService.create(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Long id) {
		return this.userService.update(user, id);
	}
}