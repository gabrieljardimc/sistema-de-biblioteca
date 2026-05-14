package dev.jardim.sistema_de_biblioteca.database.repository;

import dev.jardim.sistema_de_biblioteca.database.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
}
