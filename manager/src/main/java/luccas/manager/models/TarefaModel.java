package luccas.manager.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double horas;

    @ManyToOne
    @JoinColumn(name = "projeto")
    private ProjetoModel projeto;

}
