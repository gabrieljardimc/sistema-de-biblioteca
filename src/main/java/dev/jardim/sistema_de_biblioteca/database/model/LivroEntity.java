package dev.jardim.sistema_de_biblioteca.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String descricao;

    private Integer quantidade;

}
