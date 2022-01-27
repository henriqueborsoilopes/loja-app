package com.curso.lojaapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LojaappApplication  implements CommandLineRunner{
	

	public static void main(String[] args) {
		SpringApplication.run(LojaappApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
}
