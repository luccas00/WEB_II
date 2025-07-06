package br.ufop.edu.web2.ticket.user.domain;

import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDomain {

    private UUID id;

    private UserDomain user;

    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String region;
    private String uf;
    private String ddd;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
