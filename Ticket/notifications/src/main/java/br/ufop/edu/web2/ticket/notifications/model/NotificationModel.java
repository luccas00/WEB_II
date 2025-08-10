package br.ufop.edu.web2.ticket.notifications.model;

import br.ufop.edu.web2.ticket.notifications.enums.EnumNotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 100)
    private String service;

    @Column(nullable = false)
    private EnumNotificationType notificationType;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime sentAt;
    private LocalDateTime readAt;

    @PrePersist
    public void antesDeGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesDeAtualizar() {
        this.updatedAt = LocalDateTime.now();
    }

}
