package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegistrarVendaRequest {

    private Long clienteId;
    private Long vendedorId;
    private List<Long> patosIds;
}
