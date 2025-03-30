package dev.haymon.desafiopatos.Controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClienteRequest {

    private String nome;
    private boolean elegivelParaDesconto;
}
