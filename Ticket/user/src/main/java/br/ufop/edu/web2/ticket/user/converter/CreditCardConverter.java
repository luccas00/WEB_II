package br.ufop.edu.web2.ticket.user.converter;

import br.ufop.edu.web2.ticket.user.domain.CreditCardDomain;
import br.ufop.edu.web2.ticket.user.domain.CreditCardNetworkDomain;
import br.ufop.edu.web2.ticket.user.dtos.UserSuperRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreateCreditCardDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.SuperCreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreateCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreditCardNetworkRecordDTO;
import br.ufop.edu.web2.ticket.user.models.CreditCardModel;
import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;

import java.util.Date;
import java.util.UUID;

public class CreditCardConverter {

    // Conversão do modelo (jpa) para o DTO de saída
    public static CreditCardRecordDTO toCreditCardRecordDTO(CreditCardModel model) {
        return new CreditCardRecordDTO(
                model.getId(),
                model.getOwner(),
                model.getCreditCardNetworkModel().getName(),
                model.getCreditCardNumber(),
                model.getCvc(),
                model.getExpiryDate()
        );
    }

    // Conversão do DTO de criação para o domínio
    public static CreditCardDomain toCreditCardDomain(CreateCreditCardDTO dto) {

        return CreditCardDomain.builder()
                .owner(dto.getOwner())
                .creditCardNumber(dto.getCreditCardNumber())
                .cvc(dto.getCvc())
                .expiryDate(dto.getExpiryDate())
                .build();

    }

    public static CreditCardModel toCreditCardModel(CreditCardDomain domain) {
        return CreditCardModel.builder()
                .id(domain.getId())
                // Não atribua o usuário para evitar loop
                //.user(UserConverter.toUserModel(domain.getUser()))
                .creditCardNetworkModel(CreditCardNetworkConverter.toCreditCardNetworkModel(domain.getCreditCardNetworkDomain()))
                .creditCardNumber(domain.getCreditCardNumber())
                .cvc(domain.getCvc())
                .owner(domain.getOwner())
                .expiryDate(domain.getExpiryDate())
                .build();
    }

    public static SuperCreditCardRecordDTO toSuperCreditCardRecordDTO(CreditCardModel model) {
        return SuperCreditCardRecordDTO.builder()
                .id(model.getId())
                .owner(model.getOwner())
                .creditCardNumber(model.getCreditCardNumber())
                .cvc(model.getCvc())
                .expiryDate(model.getExpiryDate())
                .creditCardNetwork(CreditCardNetworkConverter.toSimpleCreditCardNetworkDTO(model.getCreditCardNetworkModel()))
                .build();
    }

}
