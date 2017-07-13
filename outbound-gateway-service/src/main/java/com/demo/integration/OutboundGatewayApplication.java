package com.demo.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ImportResource("integration-context.xml")
@RestController
public class OutboundGatewayApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(OutboundGatewayApplication.class);

	@Autowired
	private SimpleGateway gateway;
	
	public static void main(String[] args) {
		SpringApplication.run(OutboundGatewayApplication.class, args);
	}
	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@GetMapping("/composite")
	public String  getValues(){
		logger.info("sending request to demo-service");
		return this.gateway.execute("hello");
	}
	

}
