package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CadastroPatoRequest {

    private String nome;
    private Long maeId;
}
