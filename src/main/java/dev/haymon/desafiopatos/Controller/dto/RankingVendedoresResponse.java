package dev.haymon.desafiopatos.Controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RankingVendedoresResponse {

    private int lugar;
    private Long vendedorId;
    private String nome;
    private int totalDeVendas;
    private int patosVendidos;
    private BigDecimal valorTotalVendido;
}
