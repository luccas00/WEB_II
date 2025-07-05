package br.ufop.edu.web2.ticket.sales.repositories;

import br.ufop.edu.web2.ticket.sales.enums.EnumEventsTypes;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IEventsRepository extends JpaRepository<EventsModel, UUID> {
    List<EventsModel> findAllByType(EnumEventsTypes type);
    List<EventsModel> findByName(String name);
    List<EventsModel> findAllByNameContainingIgnoreCase(String name);
}
