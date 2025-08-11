package br.ufop.edu.web2.ticket.sales.dtos.sales;

import br.ufop.edu.web2.ticket.sales.dtos.events.EventsRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.external.UserDTO;
import br.ufop.edu.web2.ticket.sales.enums.EnumSalesStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record SalesRecordDTO (
        UUID id,
        UUID user,
        EventsRecordDTO event,
        LocalDateTime purchaseDate,
        EnumSalesStatus purchaseStatus
) {
}
