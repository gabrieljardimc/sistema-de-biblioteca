package dev.jardim.sistema_de_biblioteca.controller;

import dev.jardim.sistema_de_biblioteca.database.model.UsuarioEntity;
import dev.jardim.sistema_de_biblioteca.dto.UsuarioDto;
import dev.jardim.sistema_de_biblioteca.exception.BadRequestException;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import dev.jardim.sistema_de_biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario (@Valid @RequestBody UsuarioDto usuarioDto) throws BadRequestException {
        usuarioService.criarUsuario(usuarioDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioEntity buscarId(@Valid @PathVariable Long usuarioId) throws NotFoundException {
        return usuarioService.buscarId(usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioEntity atualizarUsuario(@Valid @PathVariable Long usuarioId,
                                          @Valid @RequestBody UsuarioDto usuarioDto) throws NotFoundException {
        return usuarioService.atualizarUsuario(usuarioId, usuarioDto);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long usuarioId) throws NotFoundException {
        usuarioService.deletarUsuario(usuarioId);
    }
}
