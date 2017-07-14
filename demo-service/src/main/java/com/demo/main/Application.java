package com.demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

      	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/customer")
	public String getCustomerByCustomerNo() {
		logger.info("Inside demo-service");
		return "Welcome";
	}

	@GetMapping("/Emp")
	public String getEmployeeDetails() {
		logger.info("Inside demo-service");
		String employeeDetails=restTemplate.getForObject("http://localhost:5003/employee", String.class);
		logger.info("employee service returned: " + employeeDetails);
		return employeeDetails;
	}
	
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
}
