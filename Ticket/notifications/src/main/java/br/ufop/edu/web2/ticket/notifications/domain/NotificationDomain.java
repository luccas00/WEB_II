package br.ufop.edu.web2.ticket.notifications.domain;

import br.ufop.edu.web2.ticket.notifications.enums.EnumNotificationType;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDomain {

    private UUID id;
    private UUID userId; // UserDomain

    private String service;
    private EnumNotificationType notificationType;

    private String subject;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime sentAt;
    private LocalDateTime readAt;

}
