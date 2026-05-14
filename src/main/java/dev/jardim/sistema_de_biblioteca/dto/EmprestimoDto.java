package dev.jardim.sistema_de_biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmprestimoDto {

    @NotNull
    private LocalDate dataEmprestimo;

    @NotNull
    private LocalDate dataDevolucao;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long livroId;
}