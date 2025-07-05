package br.ufop.edu.web2.ticket.user.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumUserStatus {

    ACTIVE(1, "Active"),
    INACTIVE(2, "Inactive"),
    SUSPENDED(3, "Suspended"),
    BANNED(4, "Banned"),
    PENDING(5, "Pending"),
    DELETED(6, "Deleted");

    private Integer id;
    private String description;

}
