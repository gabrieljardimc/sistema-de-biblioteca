package dev.jardim.sistema_de_biblioteca.database.repository;

import dev.jardim.sistema_de_biblioteca.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
