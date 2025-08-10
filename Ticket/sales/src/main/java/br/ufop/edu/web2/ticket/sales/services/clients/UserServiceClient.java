package br.ufop.edu.web2.ticket.sales.services.clients;
import java.util.List;

import br.ufop.edu.web2.ticket.sales.dtos.external.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("users-service")
public interface UserServiceClient {

    @GetMapping("/users")
    List<UserDTO> getAllUsers();
}