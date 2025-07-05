package br.ufop.edu.web2.ticket.user.service;

import br.ufop.edu.web2.ticket.user.converter.CreditCardNetworkConverter;
import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.domain.CreditCardNetworkDomain;
import br.ufop.edu.web2.ticket.user.dtos.DeleteUserDTO;
import br.ufop.edu.web2.ticket.user.dtos.UserRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreateCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreditCardNetworkRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.DeleteCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.ICreditCardNetworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardNetworkService {

    private final ICreditCardNetworkRepository creditCardNetworkRepository;

    // Get all
    public List<CreditCardNetworkRecordDTO> getAll() {

        List<CreditCardNetworkModel> creditCardNetworkModelList
                = creditCardNetworkRepository.findAll();

        return creditCardNetworkModelList
                .stream()
                .map(CreditCardNetworkConverter::toSimpleCreditCardNetworkDTO)
                .toList();

    }

    // Create
    public CreditCardNetworkRecordDTO create(
            CreateCreditCardNetworkDTO createCreditCardNetworkDTO) {

        CreditCardNetworkDomain domain =
                CreditCardNetworkConverter
                        .toCreditCardNetworkDomain(createCreditCardNetworkDTO);

        // Use cases - validações conforme as regras de negócio
        // name não pode ser nulo; name não pode repetir, ...

        CreditCardNetworkModel model =
                CreditCardNetworkConverter.toCreditCardNetworkModel(domain);


        return CreditCardNetworkConverter.toSimpleCreditCardNetworkDTO(
                creditCardNetworkRepository.save(model)
        );

    }

    // Get by id
    public CreditCardNetworkRecordDTO getCNNById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<CreditCardNetworkModel> optionalCNNModel = creditCardNetworkRepository.findById(uuid);

        if (optionalCNNModel.isEmpty()) {
            return null;
        }

        CreditCardNetworkModel cnnModel = optionalCNNModel.get();
        return CreditCardNetworkConverter.toSimpleCreditCardNetworkDTO(cnnModel);

    }

    // Delete
    public void deleteCreditCardNetwork(DeleteCreditCardNetworkDTO dto) {

        Optional<CreditCardNetworkModel> optionalCCNModel = creditCardNetworkRepository
                .findById(dto.id());

        if (optionalCCNModel.isEmpty()) {
            throw new RuntimeException("Credit Card Network not found.");
        }

        creditCardNetworkRepository.delete(optionalCCNModel.get());

    }

}
