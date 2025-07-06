package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.dtos.*;
import br.ufop.edu.web2.ticket.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRecordDTO>> getUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/super")
    public ResponseEntity<List<UserSuperRecordDTO>> getUsersModels() {
        List<UserSuperRecordDTO> result = userService.getAllUsersModels()
                .stream()
                .map(UserConverter::toUserSuperRecordDTO)
                .toList();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @PostMapping
    public ResponseEntity<UserRecordDTO> createUser(@RequestBody CreateUserDTO user) {
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

    @PutMapping("/update/name")
    public ResponseEntity<UserRecordDTO> updateUserName(@RequestBody UpdateUserDTO updateUserDTO) {

        UserRecordDTO simpleUserRecordDTO = userService.updateUserName(updateUserDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }

    @PutMapping("/update/phone")
    public ResponseEntity<UserRecordDTO> updateUser(@RequestBody UpdateUserPhoneDTO updateUserPhoneDTO) {

        UserRecordDTO simpleUserRecordDTO = userService.updateUserPhone(updateUserPhoneDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }

    @PutMapping("/update/password")
    public ResponseEntity<UserRecordDTO> updateUserPassword(@RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {

        UserRecordDTO simpleUserRecordDTO = userService.updateUserPassword(updateUserPasswordDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteUser(@RequestBody DeleteUserDTO deleteUserDTO) {

        userService.deleteUser(deleteUserDTO);
        return ResponseEntity.ok("User has been deleted.");

    }



}
