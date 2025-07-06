package br.ufop.edu.web2.ticket.user.service;

import br.ufop.edu.web2.ticket.user.converter.AddressConverter;
import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.domain.AddressDomain;
import br.ufop.edu.web2.ticket.user.dtos.address.AddressRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.address.CreateAddressDTO;
import br.ufop.edu.web2.ticket.user.dtos.address.DeleteAddressDTO;
import br.ufop.edu.web2.ticket.user.models.AddressModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.IAddressRepository;
import br.ufop.edu.web2.ticket.user.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final IAddressRepository addressRepository;
    private final IUserRepository userRepository;

    public List<AddressRecordDTO> getAllAddresses() {

        List<AddressModel> result = addressRepository.findAll();

        return result.stream().map(AddressConverter::toAddressRecordDTO).toList();

    }

    public AddressRecordDTO createAddress(CreateAddressDTO dto) {

        AddressDomain domain = AddressConverter.toAddresDomain(dto);

        UserModel userModel = userRepository
                .findById(dto.getUser())
                .orElseThrow(() -> new RuntimeException("Credit Card Network não encontrado"));

        domain.setUser(UserConverter.toUserDomain(userModel));

        AddressModel model = AddressConverter.toAddresModel(domain);

        model.setUser(userModel);

        return AddressConverter.toAddressRecordDTO(addressRepository.save(model));

    }

    public void deleteAddress(DeleteAddressDTO dto) {

        Optional<AddressModel> optionalModel = addressRepository.findById(dto.id());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Address not found.");
        }

        addressRepository.delete(optionalModel.get());

    }

}
