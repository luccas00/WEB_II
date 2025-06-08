package luccas.manager.service;

import lombok.AllArgsConstructor;
import luccas.manager.converter.UsuariosConverter;
import luccas.manager.domain.UsuariosDomain;
import luccas.manager.domain.usecase.CreateUsuarioUseCase;
import luccas.manager.models.Status;
import luccas.manager.models.UsuariosModel;
import luccas.manager.repositories.IUsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import luccas.manager.dto.usuarios.*;

@Service
@AllArgsConstructor
public class UsuariosService {

    IUsuariosRepository usuariosRepository;

    public List<UsuariosRecordDTO> getAllUsers() {

        List<UsuariosModel> result = usuariosRepository.findAll();

        return result.stream().map(UsuariosConverter::toRecordDTO).toList();

    }

    public List<UsuariosModel> getAllUsersModels() {

        List<UsuariosModel> result = usuariosRepository.findAll();

        return result;

    }

    public List<UsuariosRecordDTO> getAllUsers1() {

        List<UsuariosModel> result = usuariosRepository.findAll();
        List<UsuariosRecordDTO> dtos = new ArrayList<UsuariosRecordDTO>();

        for (UsuariosModel model : result) {
            dtos.add(UsuariosConverter.toRecordDTO(model));
        }

        return dtos;

    }

    public UsuariosRecordDTO createUser(CreateUsuarioDTO createUser) {

        UsuariosDomain userDomain = UsuariosConverter.toDomain(createUser);

        CreateUsuarioUseCase createUsuarioUseCase = new CreateUsuarioUseCase(userDomain);
        createUsuarioUseCase.validar();

        UsuariosModel model = UsuariosConverter.toModel(userDomain);

        model.setHash(UUID.randomUUID().toString());
        model.setPrivateKey(UUID.randomUUID());
        model.setStatus(Status.ATIVADO);
        model.criptografarSenha();
        model.setFullName(userDomain.getFirstName() + " " + userDomain.getLastName());

        return UsuariosConverter.toRecordDTO(usuariosRepository.save(model));

    }

    public UsuariosRecordDTO getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<UsuariosModel> optionalModel = usuariosRepository.findById(uuid);

        if (optionalModel.isEmpty()) {
            return null;
        }

        UsuariosModel model = optionalModel.get();
        return UsuariosConverter.toRecordDTO(model);

    }

//    public List<UserRecordDTO> getUsersContainsName(String name) {
//
//        List<UserModel> result = userRepository.findAll();
//
//        return result.stream()
//                .filter(u -> u.getName() != null && u.getName().toLowerCase().contains(name.toLowerCase()))
//                .map(UserConverter::toUserRecordDTO)
//                .collect(Collectors.toList());
//
//    }
//
//    public List<UserRecordDTO> getUsersByName(String name) {
//
//        List<UserModel> result = userRepository.findAllByNameContainsIgnoreCase(name);
//
//        return result.stream()
//                .map(UserConverter::toUserRecordDTO)
//                .toList();
//
//    }
}
