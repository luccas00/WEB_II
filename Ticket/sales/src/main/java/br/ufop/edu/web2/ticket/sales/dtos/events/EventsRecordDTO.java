package br.ufop.edu.web2.ticket.sales.dtos.events;

import br.ufop.edu.web2.ticket.sales.enums.EnumEventsTypes;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventsRecordDTO(
        UUID id,
        String name,
        String description,
        EnumEventsTypes type,
        LocalDateTime date,
        LocalDateTime startSales,
        LocalDateTime endSales,
        float price
) {
}
