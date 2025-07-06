package br.ufop.edu.web2.ticket.user.dtos;

import br.ufop.edu.web2.ticket.user.dtos.address.AddressRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import br.ufop.edu.web2.ticket.user.models.AddressModel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public record UserRecordDTO(
        UUID id,
        String name,
        String email,
        EnumUserStatus status,
        EnumUserType userType,
        String cpf,
        String phone,
        LocalDateTime dateOfBirth,
        List<CreditCardRecordDTO> creditCards,
        List<AddressRecordDTO> addresses
) {

}
