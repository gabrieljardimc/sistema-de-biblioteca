package dev.jardim.sistema_de_biblioteca.service;

import dev.jardim.sistema_de_biblioteca.database.model.UsuarioEntity;
import dev.jardim.sistema_de_biblioteca.database.repository.UsuarioRepository;
import dev.jardim.sistema_de_biblioteca.dto.UsuarioDto;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity criarUsuario(UsuarioDto usuarioDto){

        UsuarioEntity usuario = UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .telefone(usuarioDto.getTelefone())
                .build();

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioEntity> listarUsuarios(){

        return usuarioRepository.findAll();
    }

    public UsuarioEntity buscarId(Long id)  throws NotFoundException{

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public UsuarioEntity atualizarUsuario(Long id, UsuarioDto usuarioDto) throws NotFoundException{

        UsuarioEntity usuarioCadastrado = buscarId(id);

        usuarioCadastrado.setNome(usuarioDto.getNome());
        usuarioCadastrado.setEmail(usuarioDto.getEmail());
        usuarioCadastrado.setTelefone(usuarioDto.getTelefone());

        return usuarioRepository.save(usuarioCadastrado);
    }

    public void deletarUsuario(Long id) throws NotFoundException{

        UsuarioEntity usuario = buscarId(id);

        usuarioRepository.delete(usuario);
    }
}
