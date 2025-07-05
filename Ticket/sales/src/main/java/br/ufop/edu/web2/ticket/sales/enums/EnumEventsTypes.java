package br.ufop.edu.web2.ticket.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumEventsTypes {

    PALESTRA(1, "Palestra"),
    SHOW(2, "Show"),
    TEATRO(3, "Teatro"),
    CINEMA(4, "Cinema");

    // id - descrição
    private Integer id;
    private String description;

}
