package br.ufop.edu.web2.ticket.user.dtos;

import java.util.UUID;

public record UpdateUserCreditCardDTO (

    UUID id,
    String creditCardNumber,
    UUID creditCardNetworkId

) {
}
