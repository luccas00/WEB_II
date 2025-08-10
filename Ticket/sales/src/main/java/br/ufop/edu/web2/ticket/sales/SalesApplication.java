package br.ufop.edu.web2.ticket.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalesApplication.class, args);

		System.out.println("Hello There...");
		System.out.println("Sales Application is running...");

	}
}
