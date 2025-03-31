package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class PatoVendidoResponse {

    private Long patoId;
    private String nomeDoPato;
    private BigDecimal precoUnitario;
    private LocalDateTime dataDaVenda;
    private String nomeDoCliente;
}
