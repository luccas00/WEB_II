package br.ufop.edu.web2.ticket.sales.dtos.external;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(
        UUID id,
        String name,
        String email
) {

}