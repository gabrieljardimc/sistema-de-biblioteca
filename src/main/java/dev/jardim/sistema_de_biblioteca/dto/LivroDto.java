package dev.jardim.sistema_de_biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LivroDto {

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer quantidade;
}
