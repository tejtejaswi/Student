package com.teju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplicationStarter.class, args);
		System.out.println("Spring application started");
	}
}
