package br.ufop.edu.web2.ticket.user.domain;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
