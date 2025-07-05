package br.ufop.edu.web2.ticket.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_credit_card")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "credit_card_network_id")
    private CreditCardNetworkModel creditCardNetworkModel;

    private String creditCardNumber;
    private int cvc;
    private String owner;
    private Date expiryDate;

}
