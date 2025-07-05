package br.ufop.edu.web2.ticket.user.repositories;

import br.ufop.edu.web2.ticket.user.domain.CreditCardNetworkDomain;
import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ICreditCardNetworkRepository extends JpaRepository<CreditCardNetworkModel, UUID> {


}
