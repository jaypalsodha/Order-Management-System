package com.example.ec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *	Main class for Order Service microservice 
 *
 */
@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/**
	 * Method invoked after this class has been instantiated by Spring container
	 * Initializes the in-memory database.
	 *
	 * @param strings
	 * @throws Exception if problem occurs.
     */
	@Override
	public void run(String... strings) throws Exception {
					
	}	
}
