package br.ufop.edu.web2.ticket.sales.controllers;

import br.ufop.edu.web2.ticket.sales.dtos.events.*;
import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.DeleteSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.UpdateSalesStatusDTO;
import br.ufop.edu.web2.ticket.sales.services.EventsService;
import br.ufop.edu.web2.ticket.sales.services.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Service is running");
    }

    @GetMapping
    public ResponseEntity<List<EventsRecordDTO>> getAllEvents() {

        List<EventsRecordDTO> list = eventsService.getAllEvents();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<EventsRecordDTO> createEvents(@RequestBody CreateEventsDTO dto) {

        EventsRecordDTO createdEvents = eventsService.create(dto);

        return ResponseEntity.ok(createdEvents);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteEvents(@RequestBody DeleteEventsDTO dto) {

        eventsService.delete(dto);
        return ResponseEntity.ok("Event has been deleted.");

    }

    @PutMapping("/update/price")
    public ResponseEntity<EventsRecordDTO> updateEventPrice(@RequestBody UpdateEventPriceDTO dto) {

        EventsRecordDTO eventsRecordDTO = eventsService.updateEventPrice(dto);

        if (eventsRecordDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(eventsRecordDTO);

    }

    @PutMapping("/update/date")
    public ResponseEntity<EventsRecordDTO> updateEventDate(@RequestBody UpdateEventDateDTO dto) {

        EventsRecordDTO eventsRecordDTO = eventsService.updateEventDate(dto);

        if (eventsRecordDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(eventsRecordDTO);

    }
}
