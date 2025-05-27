package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/status")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Hello There - Service is running");
    }


}
