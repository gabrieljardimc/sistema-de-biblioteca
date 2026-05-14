package dev.jardim.sistema_de_biblioteca.service;

import dev.jardim.sistema_de_biblioteca.database.model.EmprestimoEntity;
import dev.jardim.sistema_de_biblioteca.database.model.LivroEntity;
import dev.jardim.sistema_de_biblioteca.database.model.UsuarioEntity;
import dev.jardim.sistema_de_biblioteca.database.repository.EmprestimoRepository;
import dev.jardim.sistema_de_biblioteca.database.repository.LivroRepository;
import dev.jardim.sistema_de_biblioteca.dto.EmprestimoDto;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    public EmprestimoEntity criarEmprestimo(EmprestimoDto dto) throws NotFoundException {

        UsuarioEntity usuario = usuarioService.buscarId(dto.getUsuarioId());
        LivroEntity livro = livroService.buscarId(dto.getLivroId());

        if (livro.getQuantidade() <= 0) {
            throw new RuntimeException("Livro indisponível");
        }

        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.save(livro);

        EmprestimoEntity emprestimo = EmprestimoEntity.builder()
                .dataEmprestimo(dto.getDataEmprestimo())
                .dataDevolucao(dto.getDataDevolucao())
                .devolvido(false)
                .usuario(usuario)
                .livro(livro)
                .build();

        return emprestimoRepository.save(emprestimo);
    }

    public List<EmprestimoEntity> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public EmprestimoEntity buscarId(Long id) throws NotFoundException {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado"));
    }

    public EmprestimoEntity atualizarEmprestimo(Long id, EmprestimoDto dto) throws NotFoundException {

        EmprestimoEntity emprestimo = buscarId(id);

        if (!emprestimo.getDevolvido()) {

            LivroEntity livro = emprestimo.getLivro();
            livro.setQuantidade(livro.getQuantidade() + 1);
            livroRepository.save(livro);

            emprestimo.setDevolvido(true);
        }

        emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        emprestimo.setDataDevolucao(dto.getDataDevolucao());

        return emprestimoRepository.save(emprestimo);
    }

    public void deletarEmprestimo(Long id) throws NotFoundException {

        EmprestimoEntity emprestimo = buscarId(id);

        if (!emprestimo.getDevolvido()) {
            LivroEntity livro = emprestimo.getLivro();
            livro.setQuantidade(livro.getQuantidade() + 1);
            livroRepository.save(livro);
        }

        emprestimoRepository.delete(emprestimo);
    }
}