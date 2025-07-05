package br.ufop.edu.web2.ticket.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumUserType {

    CUSTOMER(1, "Customer"),
    ENTERPRISE(2, "Enterprise"),
    ADMINISTRATOR(3, "Administrator");

    private Integer id;
    private String description;

}
