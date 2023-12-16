package com.example.bliblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.bliblioteca.model")
public class BlibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlibliotecaApplication.class, args);
	}

}
