package br.ufop.edu.web2.ticket.notifications.controller;

import br.ufop.edu.web2.ticket.notifications.dtos.CreateNotificationDTO;
import br.ufop.edu.web2.ticket.notifications.dtos.NotificationDTO;
import br.ufop.edu.web2.ticket.notifications.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Notification service is running.");
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> create(
            @RequestBody CreateNotificationDTO createNotificationDTO) {

        NotificationDTO notificationDTO = notificationService.create(createNotificationDTO);

        return ResponseEntity.ok(notificationDTO);

    }

}
