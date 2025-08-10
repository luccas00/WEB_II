package br.ufop.edu.web2.ticket.notifications.domain;

import br.ufop.edu.web2.ticket.notifications.enums.EnumNotificationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
