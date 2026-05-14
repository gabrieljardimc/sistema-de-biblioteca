package dev.jardim.sistema_de_biblioteca.controller;

import dev.jardim.sistema_de_biblioteca.database.model.LivroEntity;
import dev.jardim.sistema_de_biblioteca.dto.LivroDto;
import dev.jardim.sistema_de_biblioteca.dto.UsuarioDto;
import dev.jardim.sistema_de_biblioteca.exception.BadRequestException;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import dev.jardim.sistema_de_biblioteca.service.LivroService;
import dev.jardim.sistema_de_biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarLivro (@Valid @RequestBody LivroDto livroDto) throws BadRequestException {
        livroService.criarLivro(livroDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LivroEntity> listarLivros(){
        return livroService.listarLivros();
    }

    @GetMapping("/{livroId}")
    @ResponseStatus(HttpStatus.OK)
    public LivroEntity buscarId(@Valid @PathVariable Long livroId) throws NotFoundException {
        return livroService.buscarId(livroId);
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<LivroEntity> buscarId(@Valid @RequestParam String titulo) throws NotFoundException {
        return livroService.buscarTitulo(titulo);
    }

    @PutMapping("/{livroId}")
    @ResponseStatus(HttpStatus.OK)
    public LivroEntity atualizarLivro(@PathVariable Long livroId,
                                      @Valid @RequestBody LivroDto livroDto) throws NotFoundException {
        return livroService.atualizarLivro(livroId, livroDto);
    }

    @DeleteMapping("/{livroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLivro(@PathVariable Long livroId) throws NotFoundException {
        livroService.deletarLivro(livroId);
    }
}
