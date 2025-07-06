package br.ufop.edu.web2.ticket.user.domain;

import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import br.ufop.edu.web2.ticket.user.models.CreditCardModel;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDomain {

    private UUID id;
    private UUID key;
    private String name;

    private List<CreditCardDomain> creditCards = new ArrayList<>();

    private List<AddressDomain> addresses = new ArrayList<>();

    private EnumUserType userType;
    private EnumUserStatus status;

    private String email;
    private String password;

    private String cpf;

    private String phone;

    private LocalDateTime dateOfBirth;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return " - UserDomain - "
            + "\n ID: " + this.id
            + "\n Name: " + this.name
            + "\n Credit Cards Count: " + this.creditCards.size()
            + "\n Email: " + this.email
            + "\n Password: " + this.password
            + "\n CreatedAt: " + this.createdAt.format(formatter)
            + "\n UpdatedAt: " + this.updatedAt.format(formatter)
            + "\n - - - - -";
    }

}
