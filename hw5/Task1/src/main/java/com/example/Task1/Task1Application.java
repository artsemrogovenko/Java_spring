package com.example.Task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/*приложение для управления списком задач с использованием Spring Boot и Spring Data JPA*/
@SpringBootApplication
public class Task1Application {
	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

}
