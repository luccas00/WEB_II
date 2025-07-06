package br.ufop.edu.web2.ticket.sales.dtos.events;

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
public class UpdateEventDateDTO {

    UUID id;

    LocalDateTime date;
    LocalDateTime startSales;
    LocalDateTime endSales;

}
