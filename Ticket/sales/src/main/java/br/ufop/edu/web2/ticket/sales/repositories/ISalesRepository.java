package br.ufop.edu.web2.ticket.sales.repositories;

import br.ufop.edu.web2.ticket.sales.models.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISalesRepository extends JpaRepository<SalesModel, UUID> {

    List<SalesModel> findAllByOrderByIdAsc();

}
