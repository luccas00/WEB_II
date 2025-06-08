package br.ufop.edu.web2.ticket.user.repositories;

import br.ufop.edu.web2.ticket.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    List<UserModel> findByName(String name);
    List<UserModel> findByEmail(String email);
    List<UserModel> findByCity(String city);
    List<UserModel> findByStatus(int status);
    List<UserModel> findAllByNameContainsIgnoreCase(String name);
}
