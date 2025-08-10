package br.ufop.edu.web2.ticket.notifications.service;

import br.ufop.edu.web2.ticket.notifications.converter.NotificationConverter;
import br.ufop.edu.web2.ticket.notifications.domain.NotificationDomain;
import br.ufop.edu.web2.ticket.notifications.dtos.CreateNotificationDTO;
import br.ufop.edu.web2.ticket.notifications.dtos.NotificationDTO;
import br.ufop.edu.web2.ticket.notifications.model.NotificationModel;
import br.ufop.edu.web2.ticket.notifications.repositories.INotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<NotificationDTO> getAllNotifications() {

        List<NotificationModel> list = notificationRepository.findAll();

        return list.stream().map(NotificationConverter::toNotificationDTO).toList();

    }

    public List<NotificationDTO> getTop10Notifications() {

        List<NotificationModel> list = notificationRepository.findAll();

        return list.stream().map(NotificationConverter::toNotificationDTO).toList().subList(0, 10);

    }

}
