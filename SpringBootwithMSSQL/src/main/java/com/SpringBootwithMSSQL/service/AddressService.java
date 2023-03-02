package com.SpringBootwithMSSQL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootwithMSSQL.entity.Address;
import com.SpringBootwithMSSQL.exception.EntityNotFoundException;
import com.SpringBootwithMSSQL.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepo;

	public Address fetchById(final Long id) {
		Optional<Address> address = addressRepo.findById(id);
		if (!address.isPresent()) {
			throw new EntityNotFoundException("id-" + id);
		}
		return address.get();
	}

	public List<Address> fetchAll() {
		return addressRepo.findAll();
	}

	public Address create(final Address address) {
		return addressRepo.save(address);
	}

	public ResponseEntity<Object> update(final Address address, final Long id) {
		Optional<Address> addressOptional = addressRepo.findById(id);
		if (!addressOptional.isPresent()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EntityNotFoundException("id-" + id);
		}
		address.setId(id);
		addressRepo.save(address);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	public void delete(final Long id) {
		addressRepo.deleteById(id);
	}

}
