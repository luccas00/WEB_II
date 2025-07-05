package br.ufop.edu.web2.ticket.user.domain;

import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardDomain {

    private UUID id;
    private UserDomain user;
    private CreditCardNetworkDomain creditCardNetworkDomain;
    private String creditCardNumber;
    private int cvc;
    private String owner;
    private Date expiryDate;

}
