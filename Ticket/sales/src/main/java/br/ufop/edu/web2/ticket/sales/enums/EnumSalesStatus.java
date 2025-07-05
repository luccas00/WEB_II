package br.ufop.edu.web2.ticket.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumSalesStatus {

    EM_ABERTO(1, "Em aberto"),
    PAGO(2, "Pago"),
    CANCELADO(3, "Cancelado"),
    ESTORNADO(4, "Estornado");

    // id - descrição
    private Integer id;
    private String description;

}
