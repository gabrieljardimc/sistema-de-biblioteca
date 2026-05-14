package dev.jardim.sistema_de_biblioteca.database.repository;

import dev.jardim.sistema_de_biblioteca.database.model.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {
}
