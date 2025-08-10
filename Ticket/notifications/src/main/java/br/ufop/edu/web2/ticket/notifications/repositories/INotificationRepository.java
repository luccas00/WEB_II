package br.ufop.edu.web2.ticket.notifications.repositories;

import br.ufop.edu.web2.ticket.notifications.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface INotificationRepository extends JpaRepository<NotificationModel, UUID> {

    List<NotificationModel> findAllByUserId(UUID userId);
    List<NotificationModel> findAllByUserIdIn(List<UUID> userIdList);

}
