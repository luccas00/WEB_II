package br.ufop.edu.web2.ticket.user.converter;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.dtos.CreateUserRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.UserRecordDTO;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static UserRecordDTO toUserRecordDTO(UserModel user) {
        return new UserRecordDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static UserDomain toUserDomain(CreateUserRecordDTO createUserDTO) {

        return UserDomain.builder()
                .name(createUserDTO.getName())
                .creditCardNumber(createUserDTO.getCreditCardNumber())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .city(createUserDTO.getCity())
                .build();

    }

    public static UserModel toUserModel(UserDomain userDomain) {

        return UserModel.builder()
                .name(userDomain.getName())
                .creditCardNumber(userDomain.getCreditCardNumber())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .city(userDomain.getCity())
                .build();

    }

}
