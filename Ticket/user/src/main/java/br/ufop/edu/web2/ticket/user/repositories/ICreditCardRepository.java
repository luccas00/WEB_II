package br.ufop.edu.web2.ticket.user.repositories;

import br.ufop.edu.web2.ticket.user.models.CreditCardModel;
import br.ufop.edu.web2.ticket.user.models.CreditCardNetworkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICreditCardRepository extends JpaRepository<CreditCardModel, UUID> {
}
