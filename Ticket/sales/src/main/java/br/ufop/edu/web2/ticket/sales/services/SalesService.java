package br.ufop.edu.web2.ticket.sales.services;

import br.ufop.edu.web2.ticket.sales.converter.SalesConverter;
import br.ufop.edu.web2.ticket.sales.domain.SalesDomain;
import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.DeleteSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.UpdateSalesStatusDTO;
import br.ufop.edu.web2.ticket.sales.enums.EnumSalesStatus;
import br.ufop.edu.web2.ticket.sales.models.EventsModel;
import br.ufop.edu.web2.ticket.sales.models.SalesModel;
import br.ufop.edu.web2.ticket.sales.repositories.IEventsRepository;
import br.ufop.edu.web2.ticket.sales.repositories.ISalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesService {

    private final ISalesRepository salesRepository;
    private final IEventsRepository eventsRepository;

    public List<SalesRecordDTO> getAllSales() {

        List<SalesModel> salesModelList = salesRepository.findAll();

        return salesModelList.stream().map(SalesConverter::toSalesRecordDTO).toList();

    }

    public SalesRecordDTO create(CreateSalesDTO dto) {

        // Converte o DTO para domínio
        SalesDomain domain = SalesConverter.toSalesDomain(dto);

        // Busca o evento já persistido no banco
        Optional<EventsModel> optionalModel = eventsRepository.findById(dto.getEvent_id());
        if (optionalModel.isEmpty()) {
            throw new IllegalArgumentException("Evento não encontrado.");
        }

        EventsModel eventsModel = optionalModel.get();

        // Prepara o modelo
        SalesModel model = SalesModel.builder()
                .user_id(domain.getUser_id())
                .event(eventsModel)
                .purchaseDate(domain.getPurchaseDate())
                .purchaseStatus(EnumSalesStatus.EM_ABERTO)
                .build();

        SalesModel saved = salesRepository.save(model);

        return SalesConverter.toSalesRecordDTO(saved);

    }

    public void delete(DeleteSalesDTO dto) {

        Optional<SalesModel> optionalModel = salesRepository.findById(dto.id());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Sales not found.");
        }

        salesRepository.delete(optionalModel.get());

    }

    public SalesRecordDTO updateSalesStatus(UpdateSalesStatusDTO dto) {

        SalesDomain domain = SalesConverter.toSalesDomain(dto);

        Optional<SalesModel> optionalModel = salesRepository.findById(dto.getId());

        if (optionalModel.isEmpty()) {
            throw new RuntimeException("Sales not found.");
        }

        SalesModel salesModel = optionalModel.get();

        if (dto.getPurchaseStatus() != null) {
            salesModel.setPurchaseStatus(dto.getPurchaseStatus());
        }

        SalesModel saved = salesRepository.save(salesModel);

        return SalesConverter.toSalesRecordDTO(saved);

    }

}
