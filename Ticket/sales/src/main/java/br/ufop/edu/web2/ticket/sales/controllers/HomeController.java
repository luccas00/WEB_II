package br.ufop.edu.web2.ticket.sales.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Status of Sales Service is: Online");
    }

}
