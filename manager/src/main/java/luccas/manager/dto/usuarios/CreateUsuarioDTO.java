package luccas.manager.dto.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luccas.manager.models.Status;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioDTO {

    private String firstName;
    private String lastName;
    private String CPF;

    private String email;
    private String senha;
    private String celular;

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String endereco;

}
