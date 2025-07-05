package br.ufop.edu.web2.ticket.sales.controllers;

import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.DeleteSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.services.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Service is running");
    }

    @GetMapping
    public ResponseEntity<List<SalesRecordDTO>> getAllSales() {

        List<SalesRecordDTO> list = salesService.getAllSales();

        return ResponseEntity.ok(list);

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


}
