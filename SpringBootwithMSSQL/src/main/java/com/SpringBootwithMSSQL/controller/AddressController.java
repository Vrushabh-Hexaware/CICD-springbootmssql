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

import com.SpringBootwithMSSQL.entity.Address;
import com.SpringBootwithMSSQL.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/address")
	public List<Address> fetchAll() {
		return this.addressService.fetchAll();
	}

	@GetMapping("/address/{id}")
	public Address fetchById(@PathVariable Long id) {
		return this.addressService.fetchById(id);
	}

	@DeleteMapping("/address/{id}")
	public void delete(@PathVariable Long id) {
		this.addressService.delete(id);
	}

	@PostMapping("/address")
	public Address create(@RequestBody Address address) {
		return this.addressService.create(address);
	}
	
	@PutMapping("/address/{id}")
	public ResponseEntity<Object> update(@RequestBody Address address, @PathVariable Long id) {
		return this.addressService.update(address, id);
	}
}