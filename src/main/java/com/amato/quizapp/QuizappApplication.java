package com.amato.quizapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizappApplication {

	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("Current working directory: " + currentDirectory);

		SpringApplication.run(QuizappApplication.class, args);
	}

}
