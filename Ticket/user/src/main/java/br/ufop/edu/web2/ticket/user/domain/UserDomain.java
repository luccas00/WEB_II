package br.ufop.edu.web2.ticket.user.domain;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDomain {

    private UUID id;
    private String name;

    // ...

    private String creditCardNumber;

    private String email;
    private String password;

    private String city;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return " - UserDomain - "
            + "\n ID: " + this.id
            + "\n Name: " + this.name
            + "\n CreditCardNumber: " + this.creditCardNumber
            + "\n Email: " + this.email
            + "\n Password: " + this.password
            + "\n CreatedAt: " + this.createdAt.format(formatter)
            + "\n UpdatedAt: " + this.updatedAt.format(formatter)
            + "\n - - - - -";
    }

}
