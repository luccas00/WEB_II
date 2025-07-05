package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.util.Password;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Status of User Service is: Online");
    }

    @PostMapping("/decript")
    public ResponseEntity<String> decriptCodex(@RequestBody Map<String, String> payload) throws Exception {

        String senha = payload.get("senha");
        UUID key = UUID.fromString(payload.get("key"));

        return ResponseEntity.ok(Password.decrypt(key, senha));
    }

    @PostMapping("/encrypt")
    public ResponseEntity<String> encryptCodex(@RequestBody Map<String, String> payload) throws Exception {

        String senha = payload.get("senha");
        UUID key = UUID.fromString(payload.get("key"));

        return ResponseEntity.ok(Password.encrypt(key, senha));
    }

}
