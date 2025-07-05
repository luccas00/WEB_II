package br.ufop.edu.web2.ticket.user.dtos.creditcard;

import java.util.Date;
import java.util.UUID;

public record CreditCardRecordDTO (
        UUID id,
        String owner,
        String creditCardNetwork,
        String creditCardNumber,
        int cvc,
        Date expiryDate
) {
}
