package luccas.manager.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import luccas.manager.domain.UsuariosDomain;
import luccas.manager.dto.usuarios.CreateUsuarioDTO;
import luccas.manager.dto.usuarios.UsuariosRecordDTO;
import luccas.manager.models.UsuariosModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuariosConverter {

    public static UsuariosRecordDTO toRecordDTO(UsuariosModel model) {

        return new UsuariosRecordDTO(
                model.getId(),
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getSenha(),
                model.getCPF()
        );

    }

    public static UsuariosModel toModel(UsuariosDomain domain) {
        return UsuariosModel.builder()
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .email(domain.getEmail())
                .senha(domain.getSenha())
                .CPF(domain.getCPF())
                .celular(domain.getCelular())
                .cidade(domain.getCidade())
                .estado(domain.getEstado())
                .build();
    }

    public static UsuariosDomain toDomain(CreateUsuarioDTO createUsuarioDTO) {

        return UsuariosDomain.builder()
                .firstName(createUsuarioDTO.getFirstName())
                .lastName(createUsuarioDTO.getLastName())
                .email(createUsuarioDTO.getEmail())
                .senha(createUsuarioDTO.getSenha())
                .CPF(createUsuarioDTO.getCPF())
                .celular(createUsuarioDTO.getCelular())
                .cidade(createUsuarioDTO.getCidade())
                .estado(createUsuarioDTO.getEstado())
                .build();
    }

}
