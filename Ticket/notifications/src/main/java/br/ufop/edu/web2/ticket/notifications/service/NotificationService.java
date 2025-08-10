package br.ufop.edu.web2.ticket.notifications.service;

import br.ufop.edu.web2.ticket.notifications.converter.NotificationConverter;
import br.ufop.edu.web2.ticket.notifications.domain.NotificationDomain;
import br.ufop.edu.web2.ticket.notifications.dtos.CreateNotificationDTO;
import br.ufop.edu.web2.ticket.notifications.dtos.NotificationDTO;
import br.ufop.edu.web2.ticket.notifications.model.NotificationModel;
import br.ufop.edu.web2.ticket.notifications.repositories.INotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final INotificationRepository notificationRepository;

    public NotificationDTO create(CreateNotificationDTO createNotificationDTO) {

        NotificationDomain notificationDomain = NotificationConverter.toNotificationDomain(createNotificationDTO);

        // Use cases ...

        // Converter para entidade JPA
        NotificationModel notificationModel = NotificationConverter.toNotificationModel(notificationDomain);

        // Persistência e retorno - DTO de saída
        return NotificationConverter.toNotificationDTO(
                notificationRepository.save(notificationModel)
        );

    }

}
