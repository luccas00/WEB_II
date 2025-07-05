package br.ufop.edu.web2.ticket.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDTO {

    private UUID id;
    private UUID key;
    private String email;

    private String oldPassword;
    private String newPassword;

}
