package dev.haymon.desafiopatos.repository;

import dev.haymon.desafiopatos.model.Pato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatoRepository extends JpaRepository<Pato, Long> {
}
