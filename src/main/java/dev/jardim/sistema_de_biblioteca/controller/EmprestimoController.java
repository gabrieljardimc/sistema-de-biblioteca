package dev.jardim.sistema_de_biblioteca.controller;

import dev.jardim.sistema_de_biblioteca.database.model.EmprestimoEntity;
import dev.jardim.sistema_de_biblioteca.dto.EmprestimoDto;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import dev.jardim.sistema_de_biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoEntity criarEmprestimo(
            @Valid @RequestBody EmprestimoDto emprestimoDto)
            throws NotFoundException {

        return emprestimoService.criarEmprestimo(emprestimoDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmprestimoEntity> listarEmprestimos() {
        return emprestimoService.listarEmprestimos();
    }

    @GetMapping("/{emprestimoId}")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoEntity buscarId(
            @PathVariable Long emprestimoId)
            throws NotFoundException {

        return emprestimoService.buscarId(emprestimoId);
    }

    @PutMapping("/{emprestimoId}")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoEntity atualizarEmprestimo(
            @PathVariable Long emprestimoId,
            @Valid @RequestBody EmprestimoDto emprestimoDto)
            throws NotFoundException {

        return emprestimoService.atualizarEmprestimo(emprestimoId, emprestimoDto);
    }

    @PutMapping("/{emprestimoId}/devolver")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoEntity devolverEmprestimo(
            @PathVariable Long emprestimoId)
            throws NotFoundException {

        return emprestimoService.atualizarEmprestimo(
                emprestimoId,
                new EmprestimoDto()
        );
    }

    @DeleteMapping("/{emprestimoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEmprestimo(
            @PathVariable Long emprestimoId)
            throws NotFoundException {
        emprestimoService.deletarEmprestimo(emprestimoId);
    }
}