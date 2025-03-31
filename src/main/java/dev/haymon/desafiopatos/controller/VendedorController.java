package dev.haymon.desafiopatos.controller;

import dev.haymon.desafiopatos.controller.dto.VendedorRequest;
import dev.haymon.desafiopatos.controller.dto.VendedorResponse;
import dev.haymon.desafiopatos.controller.mapper.VendedorMapper;
import dev.haymon.desafiopatos.service.VendedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService service;
    private final VendedorMapper mapper;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid VendedorRequest dto) {
        service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        return service.obterPorId(id)
                .map(vendedor -> ResponseEntity.ok(mapper.toDTO(vendedor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VendedorResponse>> listarVendedores() {
        List<VendedorResponse> vendedores = service.obterVendedores().stream().map(mapper::toDTO).toList();
        return ResponseEntity.ok(vendedores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid VendedorRequest dto
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
