package com.SpringBootwithMSSQL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootwithMSSQL.entity.User;
import com.SpringBootwithMSSQL.exception.EntityNotFoundException;
import com.SpringBootwithMSSQL.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User fetchById(final Long id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new EntityNotFoundException("id-" + id);
		}
		return user.get();
	}

	public List<User> fetchAll() {
		return userRepo.findAll();
	}

	public User create(final User user) {
		return userRepo.save(user);
	}

	public ResponseEntity<Object> update(final User user, final Long id) {
		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EntityNotFoundException("id-" + id);
		}
		user.setId(id);
		userRepo.save(user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	public void delete(final Long id) {
		userRepo.deleteById(id);
	}

}
