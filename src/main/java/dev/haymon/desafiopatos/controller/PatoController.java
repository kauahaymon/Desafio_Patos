package dev.haymon.desafiopatos.controller;

import dev.haymon.desafiopatos.controller.dto.AtualizarPatoRequest;
import dev.haymon.desafiopatos.controller.dto.CadastroPatoRequest;
import dev.haymon.desafiopatos.controller.dto.PatoResponse;
import dev.haymon.desafiopatos.controller.mapper.PatoMapper;
import dev.haymon.desafiopatos.service.PatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patos")
@RequiredArgsConstructor
public class PatoController {

    private final PatoService service;
    private final PatoMapper mapper;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPatoRequest dto) {
        service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build(); /// uri location
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatoResponse> obterPorId(@PathVariable Long id) {
       return service.obterPorId(id)
               .map(pato -> ResponseEntity.ok(mapper.toDTO(pato)))
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarPatoRequest dto
    ) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
