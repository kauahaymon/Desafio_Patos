package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.Controller.dto.ClienteRequest;
import dev.haymon.desafiopatos.model.Cliente;
import dev.haymon.desafiopatos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public void cadastrar(ClienteRequest dto) {
        Cliente novoCliente = Cliente.builder()
                .nome(dto.getNome())
                .elegivelParaDesconto(dto.isElegivelParaDesconto())
                .build();
        repository.save(novoCliente);
    }


    public Optional<Cliente> obterPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Long id, ClienteRequest dto) {
        repository.findById(id).map(cliente -> {
            cliente.setNome(dto.getNome());
            cliente.setElegivelParaDesconto(dto.isElegivelParaDesconto());
            return repository.save(cliente);
        }).orElseThrow(); /// ex
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
