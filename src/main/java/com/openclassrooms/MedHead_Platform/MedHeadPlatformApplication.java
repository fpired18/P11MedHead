package com.openclassrooms.MedHead_Platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedHeadPlatformApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MedHeadPlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
		
	}
	

	
}
