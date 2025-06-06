package br.ufop.edu.web2.ticket.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRecordDTO {

    private String name;
    private String creditCardNumber;
    private String email;
    private String password;
    private String city;

}
