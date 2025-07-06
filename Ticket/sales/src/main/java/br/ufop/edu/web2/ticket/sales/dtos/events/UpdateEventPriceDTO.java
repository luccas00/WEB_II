package br.ufop.edu.web2.ticket.sales.dtos.events;

import br.ufop.edu.web2.ticket.sales.enums.EnumSalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventPriceDTO {

    UUID id;
    float price;

}
