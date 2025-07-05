package br.ufop.edu.web2.ticket.user.dtos.creditcard;

import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreditCardNetworkRecordDTO;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record SuperCreditCardRecordDTO (
        UUID id,
        String owner,
        String creditCardNumber,
        int cvc,
        Date expiryDate,
        CreditCardNetworkRecordDTO creditCardNetwork
) { }
