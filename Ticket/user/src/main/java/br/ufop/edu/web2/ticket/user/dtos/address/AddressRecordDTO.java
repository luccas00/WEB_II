package br.ufop.edu.web2.ticket.user.dtos.address;

import java.util.UUID;

public record AddressRecordDTO (
    UUID id,
    String street,
    String complement,
    String neighborhood,
    String city,
    String state,
    String region,
    String uf,
    String ddd
) {
}
