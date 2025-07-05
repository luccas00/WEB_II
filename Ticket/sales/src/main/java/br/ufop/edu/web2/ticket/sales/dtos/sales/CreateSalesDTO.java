package br.ufop.edu.web2.ticket.sales.dtos.sales;

import br.ufop.edu.web2.ticket.sales.enums.EnumSalesStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSalesDTO {

    private UUID id;
    private UUID user_id;
    private UUID event_id;
    private LocalDateTime purchaseDate;
    private EnumSalesStatus purchaseStatus;

}
