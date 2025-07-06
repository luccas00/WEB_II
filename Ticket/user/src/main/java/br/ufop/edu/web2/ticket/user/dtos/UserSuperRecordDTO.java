package br.ufop.edu.web2.ticket.user.dtos;


import br.ufop.edu.web2.ticket.user.dtos.address.AddressRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.SuperCreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import br.ufop.edu.web2.ticket.user.models.AddressModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSuperRecordDTO {

    private UUID id;
    private UUID key;
    private String name;
    private String email;
    private String password;
    private EnumUserType userType;
    private EnumUserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String cpf;
    private String phone;
    private LocalDateTime dateOfBirth;
    private List<SuperCreditCardRecordDTO> creditCards;
    private List<AddressRecordDTO> addresses;

}