package com.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticsearch.model.Customer;


public interface CustomerRepository extends ElasticsearchRepository<Customer,String>{

	List<Customer> findByFirstname(String firstName);


	int deleteByFirstname(String name);

	void deleteById(String id);

}
