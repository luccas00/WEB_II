package br.ufop.edu.web2.ticket.user.repositories;

import br.ufop.edu.web2.ticket.user.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAddressRepository extends JpaRepository<AddressModel, UUID> {

}
