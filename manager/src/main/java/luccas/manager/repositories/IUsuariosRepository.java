package luccas.manager.repositories;

import luccas.manager.models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IUsuariosRepository extends JpaRepository<UsuariosModel, UUID> {

    List<UsuariosModel> findByFirstName(String firstName);
    List<UsuariosModel> findByLastName(String lastName);
    List<UsuariosModel> findByEmail(String email);
    List<UsuariosModel> findByCPF(String cpf);
//    List<UsuariosModel> findByStatus(int status);
//    List<UsuariosModel> findAllByNameContainsIgnoreCase(String name);
}
