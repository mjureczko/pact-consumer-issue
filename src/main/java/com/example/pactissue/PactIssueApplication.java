package com.example.pactissue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PactIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PactIssueApplication.class, args);
	}

}
