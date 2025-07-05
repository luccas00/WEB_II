package br.ufop.edu.web2.ticket.sales.domain;

import br.ufop.edu.web2.ticket.sales.enums.EnumSalesStatus;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDomain {

    private UUID id;
    private UUID user_id;

    private EventsModel event;

    private LocalDateTime purchaseDate;
    private EnumSalesStatus purchaseStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
