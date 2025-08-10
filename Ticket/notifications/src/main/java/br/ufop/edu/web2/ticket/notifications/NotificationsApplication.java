package br.ufop.edu.web2.ticket.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationsApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotificationsApplication.class, args);

		System.out.println("Notifications Application is Running...");
	}

}
