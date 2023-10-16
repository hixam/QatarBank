package com.example.dataquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class DataQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataQueryApplication.class, args);
	}

}
