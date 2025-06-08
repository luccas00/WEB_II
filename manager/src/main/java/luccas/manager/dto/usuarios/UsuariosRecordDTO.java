package luccas.manager.dto.usuarios;

import java.util.UUID;

public record UsuariosRecordDTO(UUID id, String firstName, String lastName, String email, String senha, String CPF) {
}
