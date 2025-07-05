package br.ufop.edu.web2.ticket.user.dtos;

import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserRecordDTO(
        UUID id,
        String name,
        String email,
        String city,
        EnumUserStatus status,
        EnumUserType userType,
        List<CreditCardRecordDTO> creditCards

) {

}
