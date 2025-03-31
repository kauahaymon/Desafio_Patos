package dev.haymon.desafiopatos.Controller;

import dev.haymon.desafiopatos.Controller.dto.PatoVendidoResponse;
import dev.haymon.desafiopatos.Controller.dto.RegistrarVendaRequest;
import dev.haymon.desafiopatos.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
