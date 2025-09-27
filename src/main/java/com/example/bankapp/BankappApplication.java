package com.example.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.example.bankapp")
//@EnableJpaRepositories(basePackages = "com.example.bankapp.dao.repository")
@EntityScan(basePackages = "com.example.bankapp.dao.entity")
public class BankappApplication  {

	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}

}
