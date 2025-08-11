package br.ufop.edu.web2.ticket.sales.controllers;

import br.ufop.edu.web2.ticket.sales.dtos.events.EventsRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.external.UserDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.DeleteSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.UpdateSalesStatusDTO;
import br.ufop.edu.web2.ticket.sales.services.SalesService;
import br.ufop.edu.web2.ticket.sales.services.clients.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;
    private final UserServiceClient userServiceClient;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Service is running");
    }

    @GetMapping
    public ResponseEntity<List<SalesRecordDTO>> getAllSales() {

        List<SalesRecordDTO> list = salesService.getAllSales();

        return ResponseEntity.ok(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesRecordDTO> getSaleById(@PathVariable UUID id) {
        SalesRecordDTO dto = salesService.getSaleById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SalesRecordDTO> createSales(@RequestBody CreateSalesDTO dto) {

        SalesRecordDTO createdSales = salesService.create(dto);

        return ResponseEntity.ok(createdSales);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteSales(@RequestBody DeleteSalesDTO dto) {

        salesService.delete(dto);
        return ResponseEntity.ok("Sale has been deleted.");

    }

    @PutMapping("/update/status")
    public ResponseEntity<SalesRecordDTO> updateSales(@RequestBody UpdateSalesStatusDTO dto) {

        SalesRecordDTO salesRecordDTO = salesService.updateSalesStatus(dto);

        if (salesRecordDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(salesRecordDTO);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userServiceClient.getAllUsers()
        );
    }


}
