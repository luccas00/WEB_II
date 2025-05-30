package br.ufop.edu.web2.ticket.user.converter;

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

}
