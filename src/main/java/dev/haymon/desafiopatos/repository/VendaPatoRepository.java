package dev.haymon.desafiopatos.repository;

import dev.haymon.desafiopatos.model.VendaPato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaPatoRepository extends JpaRepository<VendaPato, Long> {

    Optional<VendaPato> findByPatoId(Long id);
}
