package br.ufop.edu.web2.ticket.user;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);

		System.out.println("Hello World!");

//		String nome = "Luccas";
//		int idade = 21;
//		double salario = 10000.00;
//		float preco = 100.00f;
//
//		System.out.println("Nome: " + nome);
//		System.out.println("Idade: " + idade);
//		System.out.println("Salario: " + salario);
//		System.out.println("Preco: " + preco);
//
//		UserDomain user = new UserDomain();
//		UserDomain user2 = new UserDomain();
//
//		UUID uuid = UUID.randomUUID();
//		LocalDateTime now = LocalDateTime.now();
//		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
//
//		UserDomain completeUser = new UserDomain(uuid, "Luccas", "123", "luccas.carneiro@email.com", "123456", now, tomorrow);
//
//		System.out.println(completeUser);
//		System.out.println(completeUser.toString());

	}

}
