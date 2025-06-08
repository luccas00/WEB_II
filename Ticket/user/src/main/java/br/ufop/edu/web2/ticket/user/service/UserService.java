package br.ufop.edu.web2.ticket.user.service;

import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.domain.usecase.CreateUserUseCase;
import br.ufop.edu.web2.ticket.user.dtos.CreateUserRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.UserRecordDTO;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository userRepository;

    public List<UserRecordDTO> getAllUsers() {

        List<UserModel> result = userRepository.findAll();

        return result.stream().map(UserConverter::toUserRecordDTO).toList();

    }

    public List<UserRecordDTO> getAllUsers1() {

        List<UserModel> result = userRepository.findAll();
        List<UserRecordDTO> dtos = new ArrayList<UserRecordDTO>();

        for (UserModel user : result) {
            dtos.add(UserConverter.toUserRecordDTO(user));
        }

        return dtos;

    }

    public UserRecordDTO createUser(CreateUserRecordDTO createUser) {

        UserDomain userDomain = UserConverter.toUserDomain(createUser);

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userDomain);
        createUserUseCase.validate();

        UserModel userModel = UserConverter.toUserModel(userDomain);

        return UserConverter.toUserRecordDTO(userRepository.save(userModel));

    }

    public UserRecordDTO getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<UserModel> optionalUserModel = userRepository.findById(uuid);

        if (optionalUserModel.isEmpty()) {
            return null;
        }

        UserModel userModel = optionalUserModel.get();
        return UserConverter.toUserRecordDTO(userModel);

    }

    public List<UserRecordDTO> getUsersContainsName(String name) {

        List<UserModel> result = userRepository.findAll();

        return result.stream()
                .filter(u -> u.getName() != null && u.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserConverter::toUserRecordDTO)
                .collect(Collectors.toList());

    }

    public List<UserRecordDTO> getUsersByName(String name) {

        List<UserModel> result = userRepository.findAllByNameContainsIgnoreCase(name);

        return result.stream()
                .map(UserConverter::toUserRecordDTO)
                .toList();

    }



}
