package br.ufop.edu.web2.ticket.sales.models;

import br.ufop.edu.web2.ticket.sales.enums.EnumEventsTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_events")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;

    private EnumEventsTypes type;

    private LocalDateTime Date;
    private LocalDateTime startSales;
    private LocalDateTime endSales;

    private float price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void antesGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesAtualizar(){
        this.updatedAt = LocalDateTime.now();
    }


}
