package dev.haymon.desafiopatos.repository;

import dev.haymon.desafiopatos.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);
}
