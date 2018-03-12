package com.example.demo.beans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationBeans {
	
	@LoadBalanced
	@Bean
	public RestTemplate config() {
		return new RestTemplate();
	}
}
