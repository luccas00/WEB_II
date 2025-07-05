package br.ufop.edu.web2.ticket.user.controllers;

import br.ufop.edu.web2.ticket.user.dtos.DeleteUserDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreateCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.CreditCardNetworkRecordDTO;
import br.ufop.edu.web2.ticket.user.dtos.creditcardnetwork.DeleteCreditCardNetworkDTO;
import br.ufop.edu.web2.ticket.user.service.CreditCardNetworkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ccn")
@AllArgsConstructor
public class CreditCardNetworkController {

    private final CreditCardNetworkService creditCardNetworkService;

    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @GetMapping
    public ResponseEntity<List<CreditCardNetworkRecordDTO>> getAll() {

        return ResponseEntity.ok(
                creditCardNetworkService.getAll()
        );

    }

    @PostMapping
    public ResponseEntity<CreditCardNetworkRecordDTO>
    create(@RequestBody CreateCreditCardNetworkDTO createCreditCardNetworkDTO) {

        CreditCardNetworkRecordDTO simpleCreditCardNetworkDTO
                = creditCardNetworkService.create(createCreditCardNetworkDTO);

        if (simpleCreditCardNetworkDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(simpleCreditCardNetworkDTO);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteCreditCardNetwork(@RequestBody DeleteCreditCardNetworkDTO dto) {

        creditCardNetworkService.deleteCreditCardNetwork(dto);
        return ResponseEntity.ok("Credit Card Network has been deleted.");

    }

}
