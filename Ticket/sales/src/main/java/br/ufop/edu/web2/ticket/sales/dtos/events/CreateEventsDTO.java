package br.ufop.edu.web2.ticket.sales.dtos.events;

import br.ufop.edu.web2.ticket.sales.enums.EnumEventsTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventsDTO {

    String name;
    String description;
    EnumEventsTypes type;
    LocalDateTime date;
    LocalDateTime startSales;
    LocalDateTime endSales;
    float price;

}
