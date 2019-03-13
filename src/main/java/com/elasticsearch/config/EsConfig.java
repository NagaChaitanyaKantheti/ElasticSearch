package com.elasticsearch.config;
	
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Configuration
@EnableElasticsearchRepositories("com.elasticsearch.repository")
public class EsConfig {
	

	@Value("${elasticsearch.clustername}")
	String clusterName;
	
	@Value("${elasticsearch.host}")
	String hostName;
	

	@Bean
	public TransportClient client() throws Exception {
		Settings settings = Settings.builder().put("cluster.name", clusterName).build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(InetAddress.getByName(hostName), 9300)));
		return client;

	}

}