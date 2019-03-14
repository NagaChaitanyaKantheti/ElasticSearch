package com.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.model.Customer;
import com.elasticsearch.service.QueryDslService;

@RestController
public class QueryDslController {

	@Autowired
	private QueryDslService queryDslService;
	
	@GetMapping("/serachMultiField/{firstname}/{age}")
	public List<Customer> searchByMultiField(@PathVariable String firstname, @PathVariable int age) {
		return queryDslService.searchMultifield(firstname, age);
	}
	
	@GetMapping("/customer/id/{id}")
	public List<Customer> searchById(@PathVariable String id) {
		return queryDslService.searchById(id);
	}
	
	@GetMapping("/customSearch/{firstName}")
	public List<Customer> getCustomerByField(@PathVariable String firstName) {
		return queryDslService.getCustomerSerachData(firstName);
	}

	@GetMapping("/search/{text}")
	public List<Customer> doMultimatchQuery(@PathVariable String text) {
		return queryDslService.multiMatchQuery(text);
	}
}
