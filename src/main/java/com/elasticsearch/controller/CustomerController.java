package com.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.model.Customer;
import com.elasticsearch.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	@PostMapping("/add/customer")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		repository.saveAll(customers);
		return customers.size();
	}

	@GetMapping("/customer")
	public Iterable<Customer> findAllCustomers() {
		return repository.findAll();
	}

	@GetMapping("/customer/{firstName}")
	public List<Customer> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public String deleteCustomerById(@PathVariable String id) {
		repository.deleteById(id);
		return "deleted successfully";
	}
	
	@DeleteMapping("/customer/deleteByName/{name}")
	public int deleteCustomerByName(@PathVariable String name) {
		return repository.deleteByFirstname(name);
	}
	
	
	
}
