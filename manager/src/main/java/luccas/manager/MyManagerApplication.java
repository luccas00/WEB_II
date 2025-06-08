package luccas.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyManagerApplication.class, args);

		System.out.println("Hello There!");
		System.out.println("Server is running!");


	}

}
