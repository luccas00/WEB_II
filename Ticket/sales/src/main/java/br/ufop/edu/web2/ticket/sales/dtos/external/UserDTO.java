package br.ufop.edu.web2.ticket.sales.dtos.external;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email
) {

}