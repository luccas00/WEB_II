package br.ufop.edu.web2.ticket.user.dtos.address;

import br.ufop.edu.web2.ticket.user.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {

    private UUID user;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String region;
    private String uf;
    private String ddd;
}
