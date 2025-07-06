package br.ufop.edu.web2.ticket.sales.converter;

import br.ufop.edu.web2.ticket.sales.domain.EventsDomain;
import br.ufop.edu.web2.ticket.sales.domain.SalesDomain;
import br.ufop.edu.web2.ticket.sales.dtos.events.CreateEventsDTO;
import br.ufop.edu.web2.ticket.sales.dtos.events.EventsRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.events.UpdateEventDateDTO;
import br.ufop.edu.web2.ticket.sales.dtos.events.UpdateEventPriceDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import br.ufop.edu.web2.ticket.sales.models.SalesModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventsConverter {

    public static EventsRecordDTO toEventsRecordDTO(EventsModel model) {
        return new EventsRecordDTO(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getType(),
                model.getDate(),
                model.getStartSales(),
                model.getEndSales(),
                model.getPrice()
        );
    }

    public static EventsDomain toEventsDomain(CreateEventsDTO dto) {

        return EventsDomain.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .type(dto.getType())
                .date(dto.getDate())
                .startSales(dto.getStartSales())
                .endSales(dto.getEndSales())
                .price(dto.getPrice())
                .build();

    }

    public static EventsDomain toEventsDomain(UpdateEventPriceDTO dto) {

        return EventsDomain.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .build();

    }

    public static EventsDomain toEventsDomain(UpdateEventDateDTO dto) {

        return EventsDomain.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .startSales(dto.getStartSales())
                .endSales(dto.getEndSales())
                .build();

    }

    public static EventsModel toEventsModel(EventsDomain domain) {

        return EventsModel.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .type(domain.getType())
                .date(domain.getDate())
                .startSales(domain.getStartSales())
                .endSales(domain.getEndSales())
                .price(domain.getPrice())
                .build();

    }

}
