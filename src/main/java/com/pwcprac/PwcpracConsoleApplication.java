package com.pwcprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.config.TestConfiguration;

@SpringBootApplication
@EnableEurekaClient
//provider application name
//@RibbonClient(name="user-service")//,configuration = TestConfiguration.class
@EnableFeignClients
@EnableCircuitBreaker
//@EnableAuthorizationServer
//@EnableHystrixDashboard
public class PwcpracConsoleApplication {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PwcpracConsoleApplication.class, args);
	}
}
