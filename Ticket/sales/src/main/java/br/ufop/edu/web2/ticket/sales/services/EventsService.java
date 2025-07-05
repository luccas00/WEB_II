package br.ufop.edu.web2.ticket.sales.services;

import br.ufop.edu.web2.ticket.sales.converter.EventsConverter;
import br.ufop.edu.web2.ticket.sales.domain.EventsDomain;
import br.ufop.edu.web2.ticket.sales.dtos.events.CreateEventsDTO;
import br.ufop.edu.web2.ticket.sales.dtos.events.DeleteEventsDTO;
import br.ufop.edu.web2.ticket.sales.dtos.events.EventsRecordDTO;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import br.ufop.edu.web2.ticket.sales.repositories.IEventsRepository;
import br.ufop.edu.web2.ticket.sales.repositories.ISalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventsService {

    private final ISalesRepository salesRepository;
    private final IEventsRepository eventsRepository;

    public List<EventsRecordDTO> getAllEvents() {

        List<EventsModel> eventsModelList = eventsRepository.findAll();

        return eventsModelList.stream().map(EventsConverter::toEventsRecordDTO).toList();

    }

    public EventsRecordDTO create(CreateEventsDTO dto) {

        EventsDomain domain = EventsConverter.toEventsDomain(dto);

        EventsModel model = EventsConverter.toEventsModel(domain);

        EventsModel saved = eventsRepository.save(model);

        return EventsConverter.toEventsRecordDTO(saved);

    }

    public void delete(DeleteEventsDTO dto) {

        Optional<EventsModel> optionalModel = eventsRepository.findById(dto.id());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Events not found.");
        }

        eventsRepository.delete(optionalModel.get());

    }



}
