package dev.jardim.sistema_de_biblioteca.database.repository;

import dev.jardim.sistema_de_biblioteca.database.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    List<LivroEntity> findByTituloContainingIgnoreCase(String titulo);
}
