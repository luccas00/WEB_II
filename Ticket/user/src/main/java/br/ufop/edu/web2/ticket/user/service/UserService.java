package br.ufop.edu.web2.ticket.user.service;


import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.domain.usecase.CreateUserUseCase;
import br.ufop.edu.web2.ticket.user.domain.usecase.UpdateUserPasswordUseCase;
import br.ufop.edu.web2.ticket.user.dtos.*;
import br.ufop.edu.web2.ticket.user.models.AddressModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.IAddressRepository;
import br.ufop.edu.web2.ticket.user.repositories.ICreditCardNetworkRepository;
import br.ufop.edu.web2.ticket.user.repositories.IUserRepository;
import br.ufop.edu.web2.ticket.user.util.Password;
import br.ufop.edu.web2.ticket.user.util.ViaCEP;
import br.ufop.edu.web2.ticket.user.util.ViaCepResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final IAddressRepository addressRepository;
    private final ICreditCardNetworkRepository creditCardNetworkRepository;


    public List<UserRecordDTO> getAllUsers() {

        List<UserModel> result = userRepository.findAll();

        return result.stream().map(UserConverter::toUserRecordDTO).toList();

    }

    public List<UserModel> getAllUsersModels() {
        return userRepository.findAll();
    }

    public UserRecordDTO createUser(CreateUserDTO createUser) {

        UserDomain userDomain = UserConverter.toUserDomain(createUser);

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userDomain);

        try {

            createUserUseCase.validate();

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());

        }

        UserModel userModel = UserConverter.toUserModel(userDomain);

//        CreditCardNetworkModel networkModel = creditCardNetworkRepository
//                .findById(createUser.getCreditCardNetworkId())
//                .orElseThrow(() -> new RuntimeException("Credit Card Network não encontrado"));
//
//        userModel.setCreditCardNetworkModel(networkModel);

        UserRecordDTO userRecordDTO = UserConverter.toUserRecordDTO(userRepository.save(userModel));

        AddressModel model = new AddressModel();
        model.setUser(userModel);
        model.setZipCode(createUser.getCep());


        String response = ViaCEP.buscarEndereco(createUser.getCep());

        // Parse da resposta JSON
        ObjectMapper mapper = new ObjectMapper();

        ViaCepResponse endereco = null;

        try {
            endereco = mapper.readValue(response, ViaCepResponse.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar resposta do ViaCEP", e);
        }

        model.setUser(userModel);
        model.setZipCode(endereco.getCep());
        model.setStreet(endereco.getLogradouro());
        model.setComplement(endereco.getComplemento());
        model.setNeighborhood(endereco.getBairro());
        model.setCity(endereco.getLocalidade());
        model.setState(endereco.getEstado());
        model.setUf(endereco.getUf());
        model.setRegion(endereco.getRegiao());
        model.setDdd(endereco.getDdd());

        addressRepository.save(model);

        return userRecordDTO;

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

    public UserRecordDTO updateUserName(UpdateUserDTO updateUserDTO) {

        // Converter para uma entidade do domínio
        UserDomain userDomain = UserConverter.toUserDomain(updateUserDTO);

        // Validar conforme as usecases


        // Recuperar a entidade atual do banco de dados
        // Verificar se o ID existe
        Optional<UserModel> optionalUserModel = userRepository.findById(updateUserDTO.getId());

        if (optionalUserModel.isEmpty()) {
            return null;
        }

        UserModel existingUserModel = optionalUserModel.get();

        if (updateUserDTO.getName() != null) {
            existingUserModel.setName(updateUserDTO.getName());
        }

        UserModel savedUserModel = userRepository.save(existingUserModel);

        return UserConverter.toUserRecordDTO(savedUserModel);

    }

    public UserRecordDTO updateUserPhone(UpdateUserPhoneDTO dto) {

        // Converter para uma entidade do domínio
        UserDomain userDomain = UserConverter.toUserDomain(dto);

        Optional<UserModel> optionalUserModel = userRepository.findById(userDomain.getId());

        if (optionalUserModel.isEmpty()) {
            return null;
        }

        UserModel existingUserModel = optionalUserModel.get();

        if (dto.getPhone() != null) {
            existingUserModel.setPhone(dto.getPhone());
        }

        UserModel savedUserModel = userRepository.save(existingUserModel);

        return UserConverter.toUserRecordDTO(savedUserModel);

    }

    public UserRecordDTO updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO)  {

        Optional<UserModel> optionalUserModel = userRepository.findById(updateUserPasswordDTO.getId());

        if (optionalUserModel.isEmpty()) {
            return null;
        }

        UserModel userModel = optionalUserModel.get();

        UpdateUserPasswordUseCase useCase = new UpdateUserPasswordUseCase(userModel.getKey(), userModel.getEmail(), updateUserPasswordDTO.getEmail(), userModel.getPassword(), updateUserPasswordDTO.getOldPassword());
        useCase.validate();

        try {
            userModel.setPassword(Password.encrypt(userModel.getKey(), updateUserPasswordDTO.getNewPassword()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


        return UserConverter.toUserRecordDTO(userRepository.save(userModel));

    }

    public void deleteUser(DeleteUserDTO deleteUserDTO) {

        Optional<UserModel> optionalUserModel = userRepository
                .findById(deleteUserDTO.id());

        // Use case: tickets associados ...
        if (optionalUserModel.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        userRepository.delete(optionalUserModel.get());

    }


}
