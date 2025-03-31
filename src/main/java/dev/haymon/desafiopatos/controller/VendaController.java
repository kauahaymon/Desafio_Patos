package dev.haymon.desafiopatos.controller;

import dev.haymon.desafiopatos.controller.dto.PatoVendidoResponse;
import dev.haymon.desafiopatos.controller.dto.RankingVendedoresResponse;
import dev.haymon.desafiopatos.controller.dto.RegistrarVendaRequest;
import dev.haymon.desafiopatos.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistrarVendaRequest dto) {
        service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/patos-vendidos")
    public ResponseEntity<List<PatoVendidoResponse>> listarPatosVendidos() {
        return ResponseEntity.ok(service.listarPatosVendidos());
    }

    @GetMapping("/ranking-vendedores")
    public ResponseEntity<List<RankingVendedoresResponse>> rankingVendedores(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFim
    ) {
        return ResponseEntity.ok(service.obterRankingVendedores(dataInicio, dataFim));
    }
}
