package br.ufop.edu.web2.ticket.sales.converter.external;

import br.ufop.edu.web2.ticket.sales.dtos.external.UserDTO;
import br.ufop.edu.web2.ticket.user.models.UserModel;

import java.util.ArrayList;

public class UserConverter {

    public static UserDTO toUserDTO(UserModel user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
