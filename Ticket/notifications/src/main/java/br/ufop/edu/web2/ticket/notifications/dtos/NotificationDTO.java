package br.ufop.edu.web2.ticket.notifications.dtos;

import br.ufop.edu.web2.ticket.notifications.enums.EnumNotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDTO(

        UUID id,
        UUID userId,
        String service,
        EnumNotificationType notificationType,
        String subject,
        String content,
        LocalDateTime sentAt,
        LocalDateTime readAt

) {

}
