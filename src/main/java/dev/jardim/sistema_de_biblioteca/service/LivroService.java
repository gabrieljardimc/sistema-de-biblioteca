package dev.jardim.sistema_de_biblioteca.service;

import dev.jardim.sistema_de_biblioteca.database.model.LivroEntity;
import dev.jardim.sistema_de_biblioteca.database.repository.LivroRepository;
import dev.jardim.sistema_de_biblioteca.dto.LivroDto;
import dev.jardim.sistema_de_biblioteca.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroEntity criarLivro(LivroDto livroDto) {
        LivroEntity livro = LivroEntity.builder()
                .titulo(livroDto.getTitulo())
                .autor(livroDto.getAutor())
                .descricao(livroDto.getDescricao())
                .quantidade(livroDto.getQuantidade())
                .build();

        return livroRepository.save(livro);
    }

    public List<LivroEntity> listarLivros() {

        return livroRepository.findAll();
    }

    public LivroEntity buscarId(Long id) throws NotFoundException {

        return livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O livro não foi encontrado"));
    }

    public List<LivroEntity> buscarTitulo(String titulo) throws NotFoundException {

        List<LivroEntity> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);

        if (livros.isEmpty()) {
            throw new NotFoundException("O livro não foi encontrado");
        }

        return livros;
    }

    public LivroEntity atualizarLivro(Long id, LivroDto livroDto) throws NotFoundException {

        LivroEntity livroCadastrado = buscarId(id);

        livroCadastrado.setTitulo(livroDto.getTitulo());
        livroCadastrado.setAutor(livroDto.getAutor());
        livroCadastrado.setDescricao(livroDto.getDescricao());
        livroCadastrado.setQuantidade(livroDto.getQuantidade());

        return livroRepository.save(livroCadastrado);
    }

    public void deletarLivro(Long id) throws NotFoundException{
        LivroEntity livro = buscarId(id);

        livroRepository.delete(livro);

    }


}
