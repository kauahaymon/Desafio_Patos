package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.Controller.dto.VendedorRequest;
import dev.haymon.desafiopatos.model.Vendedor;
import dev.haymon.desafiopatos.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository repository;

    public void cadastrar(VendedorRequest dto) {
        Vendedor novoVendedor = Vendedor.builder()
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .matricula(dto.getMatricula())
                .build();
        repository.save(novoVendedor);
    }

    public Optional<Vendedor> obterPorId(Long id) {
        return repository.findById(id);
    }

    public List<Vendedor> obterVendedores() {
        return repository.findAll();
    }

    public void atualizar(Long id, VendedorRequest dto) {
        repository.findById(id).map(vendedor -> {
            vendedor.setNome(dto.getNome());
            vendedor.setCpf(dto.getCpf());
            vendedor.setMatricula(dto.getMatricula());
            return repository.save(vendedor);
        }).orElseThrow(); /// ex
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
