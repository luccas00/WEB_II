package br.ufop.edu.web2.ticket.sales.converter;

import br.ufop.edu.web2.ticket.sales.domain.SalesDomain;
import br.ufop.edu.web2.ticket.sales.dtos.sales.CreateSalesDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.SalesRecordDTO;
import br.ufop.edu.web2.ticket.sales.dtos.sales.UpdateSalesStatusDTO;
import br.ufop.edu.web2.ticket.sales.models.SalesModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalesConverter {

    public static SalesRecordDTO toSalesRecordDTO(SalesModel model) {
        return  new SalesRecordDTO(
                model.getId(),
                model.getUser_id(),
                EventsConverter.toEventsRecordDTO(model.getEvent()),
                model.getPurchaseDate(),
                model.getPurchaseStatus()
        );
    }

    public static SalesDomain toSalesDomain(CreateSalesDTO dto) {

        return SalesDomain.builder()
                .user_id(dto.getUser_id())
                .purchaseDate(dto.getPurchaseDate())
                .build();

    }

    public static SalesDomain toSalesDomain(UpdateSalesStatusDTO dto) {

        return SalesDomain.builder()
                .id(dto.getId())
                .purchaseStatus(dto.getPurchaseStatus())
                .build();

    }



}
