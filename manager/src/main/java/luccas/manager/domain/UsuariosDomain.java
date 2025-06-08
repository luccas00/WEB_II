package luccas.manager.domain;

import jakarta.persistence.*;
import lombok.*;
import luccas.manager.models.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsuariosDomain {

    private UUID id;

    private String firstName;
    private String lastName;
    private String fullName;
    private String CPF;

    private UUID privateKey;
    private String hash;
    private String email;
    private String senha;
    private String celular;

    private String cidade;
    private String estado;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
