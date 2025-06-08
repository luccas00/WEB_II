package luccas.manager.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigo;
    private String nome;
    private String descricao;
    private double valor;
    private int quantidade;

}
