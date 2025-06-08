package luccas.manager.controllers;

import lombok.AllArgsConstructor;
import luccas.manager.dto.usuarios.CreateUsuarioDTO;
import luccas.manager.dto.usuarios.UsuariosRecordDTO;
import luccas.manager.models.UsuariosModel;
import luccas.manager.service.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    @GetMapping
    public ResponseEntity<List<UsuariosRecordDTO>> getUsuarios() {

        return ResponseEntity.ok(usuariosService.getAllUsers());

    }

    @GetMapping("/super")
    public ResponseEntity<List<UsuariosModel>> getUsuariosAllData() {

        return ResponseEntity.ok(usuariosService.getAllUsersModels());

    }

    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @PostMapping("/new")
    public ResponseEntity<UsuariosRecordDTO> createUser(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
        UsuariosRecordDTO usuariosRecordDTO = usuariosService.createUser(createUsuarioDTO);
        return ResponseEntity.ok(usuariosRecordDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosRecordDTO> getUserById(@PathVariable(value = "id") String id) {

        UsuariosRecordDTO usuariosRecordDTO = usuariosService.getUserById(id);

        if (usuariosRecordDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuariosRecordDTO);
        }

    }

    @PostMapping("/get")
    public ResponseEntity<UsuariosRecordDTO> getUserById(@RequestBody Map<String, String> payload) {

        String id = payload.get("id");
        UsuariosRecordDTO usuariosRecordDTO = usuariosService.getUserById(id);

        if (usuariosRecordDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuariosRecordDTO);
        }

    }



}
