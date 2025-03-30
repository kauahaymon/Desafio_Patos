package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.Controller.dto.AtualizarPatoRequest;
import dev.haymon.desafiopatos.Controller.dto.CadastroPatoRequest;
import dev.haymon.desafiopatos.model.Pato;
import dev.haymon.desafiopatos.repository.PatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatoService {

    private final PatoRepository repository;

    public Pato cadastrar(CadastroPatoRequest dto) {

        Pato novoPato = Pato.builder()
                .nome(dto.getNome())
                .vendido(false)
                .build();

        if (dto.getMaeId() != null) {
            Pato mae = repository.findById(dto.getMaeId()).orElse(null);
            novoPato.setMae(mae);
        }

        return repository.save(novoPato);
    }

    public Optional<Pato> obterPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Long id, AtualizarPatoRequest dto) {
        repository.findById(id).map(pato -> {
            pato.setNome(dto.getNome());
            pato.setVendido(dto.isVendido());
            return repository.save(pato);
        }).orElseThrow(); /// ex
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
