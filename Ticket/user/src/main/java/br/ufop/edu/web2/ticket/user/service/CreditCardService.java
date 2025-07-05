package br.ufop.edu.web2.ticket.user.service;

import br.ufop.edu.web2.ticket.user.converter.CreditCardConverter;
import br.ufop.edu.web2.ticket.user.converter.CreditCardNetworkConverter;
import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.domain.CreditCardDomain;
import br.ufop.edu.web2.ticket.user.domain.CreditCardNetworkDomain;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreateCreditCardDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.CreditCardRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcard.DeleteCreditCardDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreateCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreditCardNetworkRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.DeleteCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.models.CreditCardModel;
import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.ICreditCardNetworkRepository;
import br.ufop.edu.web2.ticket.user.repositories.ICreditCardRepository;
import br.ufop.edu.web2.ticket.user.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardService {

    private final ICreditCardNetworkRepository creditCardNetworkRepository;
    private final ICreditCardRepository creditCardRepository;
    private final IUserRepository userRepository;

    // Get all
    public List<CreditCardRecordDTO> getAll() {

        List<CreditCardModel> creditCardNetworkModelList
                = creditCardRepository.findAll();

        return creditCardNetworkModelList
                .stream()
                .map(CreditCardConverter::toCreditCardRecordDTO)
                .toList();

    }

    // Create
    public CreditCardRecordDTO create(CreateCreditCardDTO dto) {

        // Converte o DTO para domínio
        CreditCardDomain domain = CreditCardConverter.toCreditCardDomain(dto);

        // Busca o usuário já persistido no banco
        Optional<UserModel> optionalUserModel = userRepository.findById(dto.getUserId());
        if (optionalUserModel.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        // Busca a bandeira do cartão
        Optional<CreditCardNetworkModel> optionalCCNModel = creditCardNetworkRepository.findById(dto.getCreditCardNetworkId());
        if (optionalCCNModel.isEmpty()) {
            throw new IllegalArgumentException("Bandeira do cartão não encontrada.");
        }

        UserModel userModel = optionalUserModel.get();
        CreditCardNetworkModel creditCardNetworkModel = optionalCCNModel.get();

        // Prepara o modelo de cartão
        CreditCardModel model = CreditCardModel.builder()
                .owner(domain.getOwner())
                .creditCardNumber(domain.getCreditCardNumber())
                .cvc(domain.getCvc())
                .expiryDate(domain.getExpiryDate())
                .creditCardNetworkModel(creditCardNetworkModel) // vincula FK
                .user(userModel) // vincula FK
                .build();

        // ⚠️ Garante a coerência bidirecional
        userModel.getCreditCards().add(model);

        // Salva o cartão — CASCADE.ALL garante persistência consistente
        CreditCardModel saved = creditCardRepository.save(model);

        return CreditCardConverter.toCreditCardRecordDTO(saved);
    }


    // Delete
    public void deleteCreditCard(DeleteCreditCardDTO dto) {

        Optional<CreditCardModel> optionalCCModel = creditCardRepository
                .findById(dto.id());

        if (optionalCCModel.isEmpty()) {
            throw new RuntimeException("Credit Card not found.");
        }

        creditCardRepository.delete(optionalCCModel.get());

    }

}
