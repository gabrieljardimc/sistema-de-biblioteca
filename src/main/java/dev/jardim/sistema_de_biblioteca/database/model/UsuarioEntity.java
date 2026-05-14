package dev.jardim.sistema_de_biblioteca.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String telefone;

    @OneToMany(mappedBy = "usuario")
    private List<EmprestimoEntity> emprestimos;
}
