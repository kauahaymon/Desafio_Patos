package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AtualizarPatoRequest {

    private String nome;
    private boolean vendido;
}
