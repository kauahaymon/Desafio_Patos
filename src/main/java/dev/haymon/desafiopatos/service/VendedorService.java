package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.controller.dto.VendedorRequest;
import dev.haymon.desafiopatos.exception.EntidadeNaoEncontradaException;
import dev.haymon.desafiopatos.model.Vendedor;
import dev.haymon.desafiopatos.repository.VendedorRepository;
import dev.haymon.desafiopatos.validator.VendedorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository repository;
    private final VendedorValidator validator;

    public void cadastrar(VendedorRequest dto) {
        Vendedor novoVendedor = Vendedor.builder()
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .matricula(dto.getMatricula())
                .build();
        validator.validar(novoVendedor);
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
        }).orElseThrow(() -> getVendedorNaoEncontrado(id));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw getVendedorNaoEncontrado(id);
        }
        repository.deleteById(id);
    }

    private static EntidadeNaoEncontradaException getVendedorNaoEncontrado(Long id) {
        return new EntidadeNaoEncontradaException("Vendedor com ID " + id + " não encontrado");
    }
}
