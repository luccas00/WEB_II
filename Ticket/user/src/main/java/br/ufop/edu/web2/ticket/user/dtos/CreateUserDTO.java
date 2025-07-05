package br.ufop.edu.web2.ticket.user.dtos;

import br.ufop.edu.web2.ticket.user.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    private String name;
    private EnumUserStatus status;
    private EnumUserType userType;
    private String email;
    private String password;
    private String city;

}
