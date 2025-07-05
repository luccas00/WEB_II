package br.ufop.edu.web2.ticket.user.dtos.creditcard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardDTO {

    private UUID userId;
    private UUID creditCardNetworkId;
    private String creditCardNumber;
    private int cvc;
    private String owner;
    private Date expiryDate;

}
