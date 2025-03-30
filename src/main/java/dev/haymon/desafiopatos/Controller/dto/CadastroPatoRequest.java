package dev.haymon.desafiopatos.Controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CadastroPatoRequest {

    private String nome;
    private Long maeId;
}
