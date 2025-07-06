package br.ufop.edu.web2.ticket.sales.services;

import br.ufop.edu.web2.ticket.sales.converter.EventsConverter;
import br.ufop.edu.web2.ticket.sales.converter.SalesConverter;
import br.ufop.edu.web2.ticket.sales.domain.EventsDomain;
import br.ufop.edu.web2.ticket.sales.domain.SalesDomain;
import br.ufop.edu.web2.ticket.sales.dtos.events.*;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.UpdateSalesStatusDTO;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import br.ufop.edu.web2.ticket.sales.models.SalesModel;
import br.ufop.edu.web2.ticket.sales.repositories.IEventsRepository;
import br.ufop.edu.web2.ticket.sales.repositories.ISalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public EventsRecordDTO updateEventPrice(UpdateEventPriceDTO dto) {

        EventsDomain domain = EventsConverter.toEventsDomain(dto);

        Optional<EventsModel> optionalModel = eventsRepository.findById(dto.getId());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Event not found.");
        }

        EventsModel eventsModel = optionalModel.get();

        if (dto.getPrice() > 0) {
            eventsModel.setPrice(dto.getPrice());
        }

        EventsModel saved = eventsRepository.save(eventsModel);

        return EventsConverter.toEventsRecordDTO(saved);

    }

    public EventsRecordDTO updateEventDate(UpdateEventDateDTO dto) {

        EventsDomain domain = EventsConverter.toEventsDomain(dto);

        Optional<EventsModel> optionalModel = eventsRepository.findById(dto.getId());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Event not found.");
        }

        EventsModel eventsModel = optionalModel.get();

        if (dto.getDate() != null && dto.getDate().isAfter(LocalDateTime.now())) {
            eventsModel.setDate(dto.getDate());
        }

        if (dto.getStartSales() != null && dto.getStartSales().isAfter(LocalDateTime.now())) {
            eventsModel.setStartSales(dto.getStartSales());
        }

        if (dto.getEndSales() != null && dto.getEndSales().isAfter(LocalDateTime.now())) {
            eventsModel.setEndSales(dto.getEndSales());
        }

        EventsModel saved = eventsRepository.save(eventsModel);

        return EventsConverter.toEventsRecordDTO(saved);

    }


}
