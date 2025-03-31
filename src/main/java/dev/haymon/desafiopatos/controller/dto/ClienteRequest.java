package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClienteRequest {

    private String nome;
    private boolean elegivelParaDesconto;
}
