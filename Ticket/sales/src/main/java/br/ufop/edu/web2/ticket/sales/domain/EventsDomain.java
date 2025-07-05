package br.ufop.edu.web2.ticket.sales.domain;

import br.ufop.edu.web2.ticket.sales.enums.EnumEventsTypes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsDomain {

    private UUID id;

    private String name;
    private String description;

    private EnumEventsTypes type;

    private LocalDateTime date;
    private LocalDateTime startSales;
    private LocalDateTime endSales;

    private float price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
