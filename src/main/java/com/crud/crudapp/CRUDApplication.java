package com.crud.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CRUDApplication {
	public static void main(String[] args) {
		SpringApplication.run(CRUDApplication.class, args);
	}

}
