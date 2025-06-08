package luccas.manager.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projetos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private String categoria;

}
