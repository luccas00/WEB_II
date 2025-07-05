package br.ufop.edu.web2.ticket.user.models;


import br.ufop.edu.web2.ticket.user.enums.EnumUserStatus;
import br.ufop.edu.web2.ticket.user.enums.EnumUserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID key;

    private String name;

    private String email;
    private String password;

    @Column(nullable = true)
    private String city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCardModel> creditCards = new ArrayList<>();

    private EnumUserType userType;
    private EnumUserStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void antesGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesAtualizar() {
        this.updatedAt = LocalDateTime.now();
    }


}
