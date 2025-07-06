package br.ufop.edu.web2.ticket.user.converter;

import br.ufop.edu.web2.ticket.user.domain.CreditCardNetworkDomain.*;
import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.dtos.*;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.*;
import br.ufop.edu.web2.ticket.user.service.CreditCardNetworkService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static UserRecordDTO toUserRecordDTO(UserModel user) {
        return UserRecordDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .status(user.getStatus())
                .userType(user.getUserType())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .cpf(user.getCpf())
                .creditCards(
                    user.getCreditCards() != null
                        ? user.getCreditCards().stream()
                        .map(CreditCardConverter::toCreditCardRecordDTO)
                        .toList()
                        : new ArrayList<>()
                )
                .addresses(
                    user.getAddresses() != null
                        ? user.getAddresses().stream()
                        .map(AddressConverter::toAddressRecordDTO)
                        .toList()
                        : new ArrayList<>()
                )
                .build();
    }

    public static UserModel toUserModel(UserDomain userDomain) {
        return UserModel.builder()
                .name(userDomain.getName())
                .userType(userDomain.getUserType())
                .creditCards(userDomain.getCreditCards() != null
                        ? userDomain.getCreditCards().stream()
                        .map(CreditCardConverter::toCreditCardModel)
                        .toList()
                        : new ArrayList<>())
                .addresses(userDomain.getAddresses() != null
                        ? userDomain.getAddresses().stream()
                        .map(AddressConverter::toAddresModel)
                        .toList()
                        : new ArrayList<>())
                .status(userDomain.getStatus())
                .key(userDomain.getKey())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .phone(userDomain.getPhone())
                .cpf(userDomain.getCpf())
                .dateOfBirth(userDomain.getDateOfBirth())
                .build();
    }



    public static UserDomain toUserDomain(CreateUserDTO createUserDTO) {

        return UserDomain.builder()
                .name(createUserDTO.getName())
                .userType(createUserDTO.getUserType())
                .status(createUserDTO.getStatus())
                .key(UUID.randomUUID())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .cpf(createUserDTO.getCpf())
                .phone(createUserDTO.getPhone())
                .dateOfBirth(createUserDTO.getDateOfBirth())
                .build();

    }

    public static UserDomain toUserDomain(UpdateUserDTO updateUserDTO) {
        return UserDomain.builder()
                .id(updateUserDTO.getId())
                .name(updateUserDTO.getName())
                .email(updateUserDTO.getEmail())
                .build();
    }

    public static UserDomain toUserDomain(UpdateUserPhoneDTO updateUserDTO) {
        return UserDomain.builder()
                .id(updateUserDTO.getId())
                .phone(updateUserDTO.getPhone())
                .build();
    }

    public static UserDomain toUserDomain(UserModel model) {
        return UserDomain.builder()
                .id(model.getId())
                .name(model.getName())
                .email(model.getEmail())
                .build();
    }

    public static UserSuperRecordDTO toUserSuperRecordDTO(UserModel user) {
        return UserSuperRecordDTO.builder()
                .id(user.getId())
                .key(user.getKey())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .cpf(user.getCpf())
                .userType(user.getUserType())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .creditCards(user.getCreditCards().stream()
                        .map(CreditCardConverter::toSuperCreditCardRecordDTO)
                        .toList())
                .addresses(user.getAddresses().stream()
                        .map(AddressConverter::toAddressRecordDTO)
                        .toList())
                .build();
    }


}
