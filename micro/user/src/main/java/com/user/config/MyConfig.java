package com.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	
	
	//use to call the hosts of the api
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	
	//@LoadBalanced this annotation is used to more than instance or multiple instance to distribute the load
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
