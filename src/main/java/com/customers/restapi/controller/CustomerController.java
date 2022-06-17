package com.customers.restapi.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customers.restapi.entity.CustomerEntity;
import com.customers.restapi.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customerEntity){
		try {
			return new ResponseEntity<>(customerRepository.save(customerEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerEntity>> getAllCust(){
		try {
			List<CustomerEntity> list = customerRepository.findAll();
			if(list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<CustomerEntity>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<CustomerEntity>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CustomerEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> getCustbyID(@PathVariable Integer id){
		Optional<CustomerEntity> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			return new ResponseEntity<CustomerEntity>(customerOptional.get(),HttpStatus.OK);
		}
		return new ResponseEntity<CustomerEntity>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> updateCust(@PathVariable Integer id, @RequestBody CustomerEntity customerEntity){
		Optional<CustomerEntity> custOptional = customerRepository.findById(id);
		try {
			customerEntity.setId(id);
			customerEntity.setCreateOn(custOptional.get().getCreateOn());
			customerEntity.setUpdateOn(new Date());
			
			return new ResponseEntity<CustomerEntity>(customerRepository.save(customerEntity), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CustomerEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("customers/{id}")
	public ResponseEntity<CustomerEntity> deleteCust(@PathVariable Integer id){
		try {
			Optional<CustomerEntity> custOptional = customerRepository.findById(id);
			if(custOptional.isPresent()) {
				customerRepository.delete(custOptional.get());
			}
			return new ResponseEntity<CustomerEntity>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<CustomerEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
