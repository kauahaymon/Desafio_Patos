package dev.haymon.desafiopatos.repository;

import dev.haymon.desafiopatos.model.Pato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatoRepository extends JpaRepository<Pato, Long> {

    List<Pato> findByMaeIsNullOrderByNomeAsc();

    List<Pato> findByMaeIdOrderByNomeAsc(Long id);
}
