package com.elasticsearch.service;

import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.elasticsearch.model.Customer;

@Service
public class QueryDslService {

	@Autowired
	private ElasticsearchTemplate elasticSearchTemplate;
	
	
	public List<Customer> searchMultifield(String firstname,int age){
		QueryBuilder query=QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstname", firstname))
				.must(QueryBuilders.matchQuery("age",age));
		NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withQuery(query).build();
		List<Customer> customers=elasticSearchTemplate.queryForList(nativeSearchQuery, Customer.class);
		return customers;
	}
	

	public List<Customer> searchById(String id) {
		
		QueryBuilder query=QueryBuilders.matchQuery("id", id);
		NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withQuery(query).build();
		List<Customer> customer=elasticSearchTemplate.queryForList(nativeSearchQuery, Customer.class);
		return customer;
	}


	public List<Customer> getCustomerSerachData(String firstName) {
		String search = ".*" + firstName + ".*";
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("firstname", search)).build();
		List<Customer> customers = elasticSearchTemplate.queryForList(searchQuery, Customer.class);
		return customers;
	}


	public List<Customer> multiMatchQuery(String text) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(text)
				.field("firstname").field("lastname").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
		List<Customer> customers = elasticSearchTemplate.queryForList(searchQuery, Customer.class);
		return customers;
	}
	
}
