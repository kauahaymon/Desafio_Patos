package dev.haymon.desafiopatos.repository;

import dev.haymon.desafiopatos.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByMatricula(String matricula);
}
