package br.ufop.edu.web2.ticket.notifications.dtos;

import br.ufop.edu.web2.ticket.notifications.enums.EnumNotificationType;

import java.util.UUID;

public record CreateNotificationDTO(
        UUID userId,
        String service,
        EnumNotificationType notificationType,
        String subject,
        String content
) {

}