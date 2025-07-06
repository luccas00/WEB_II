package br.ufop.edu.web2.ticket.user.converter;

import br.ufop.edu.web2.ticket.user.domain.AddressDomain;
import br.ufop.edu.web2.ticket.user.dtos.address.AddressRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.address.CreateAddressDTO;
import br.ufop.edu.web2.ticket.user.models.AddressModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressConverter {

    public static AddressRecordDTO toAddressRecordDTO(AddressModel model)
    {
        return new AddressRecordDTO(
                model.getId(),
                model.getStreet(),
                model.getComplement(),
                model.getNeighborhood(),
                model.getCity(),
                model.getState(),
                model.getRegion(),
                model.getUf(),
                model.getDdd()
        );
    }

    public static AddressDomain toAddresDomain(CreateAddressDTO dto) {
        return AddressDomain.builder()
                .ddd(dto.getDdd())
                .street(dto.getStreet())
                .complement(dto.getComplement())
                .neighborhood(dto.getNeighborhood())
                .city(dto.getCity())
                .state(dto.getState())
                .region(dto.getRegion())
                .uf(dto.getUf())
                .build();
    }

    public static AddressModel toAddresModel(AddressDomain domain) {
        return AddressModel.builder()
                .ddd(domain.getDdd())
                .user(UserConverter.toUserModel(domain.getUser()))
                .id(domain.getId())
                .street(domain.getStreet())
                .complement(domain.getComplement())
                .neighborhood(domain.getNeighborhood())
                .city(domain.getCity())
                .state(domain.getState())
                .region(domain.getRegion())
                .uf(domain.getUf())
                .build();
    }

}
