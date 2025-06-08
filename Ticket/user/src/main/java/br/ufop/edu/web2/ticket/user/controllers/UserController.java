package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.dtos.CreateUserRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.UserRecordDTO;
import br.ufop.edu.web2.ticket.user.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRecordDTO>> getUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @GetMapping("/users/report")
    public ResponseEntity<String> getUserReport() {
        return ResponseEntity.ok("This is My Report");
    }

    @PostMapping
    public ResponseEntity<UserRecordDTO> createUser(@RequestBody CreateUserRecordDTO user) {

        UserRecordDTO createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecordDTO> getUserById(@PathVariable(value = "id") String id) {

        UserRecordDTO userRecordDTO = userService.getUserById(id);

        if (userRecordDTO == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(userRecordDTO);

    }

    @PostMapping("/getById")
    public ResponseEntity<UserRecordDTO> getUserById(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        UserRecordDTO userRecordDTO = userService.getUserById(id);
        return ResponseEntity.ok(userRecordDTO);
    }

    @GetMapping("/containsName/{name}")
    public ResponseEntity<List<UserRecordDTO>> getUserContainsName(@PathVariable(value = "name") String name) {

        List<UserRecordDTO> users = userService.getUsersContainsName(name);

        return ResponseEntity.ok(users);

    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<UserRecordDTO>> getUserByName(@PathVariable(value = "name") String name) {

        List<UserRecordDTO> users = userService.getUsersByName(name);

        return ResponseEntity.ok(users);

    }

}
