package br.ufop.edu.web2.ticket.user.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.PrimaryKey;

import java.time.LocalDateTime;
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

    private String name;

    private String creditCardNumber;

    private String email;
    private String password;

    @Column(nullable = true)
    private String city;

    private String statusName;
    private int status;

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
