package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.View;

@RestController
public class UserController {

    @GetMapping("/users/status")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @GetMapping("/users/report")
    public ResponseEntity<String> getAllUsers1() {
        return ResponseEntity.ok("This is My Report");
    }

    @GetMapping("/users/all")
    public ResponseEntity<String> getAllUsers2() {
        return ResponseEntity.ok("All Users are running");
    }

}
