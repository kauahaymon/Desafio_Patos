package dev.haymon.desafiopatos.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatoResponse {

    private Long id;
    private String nome;
    private boolean vendido;
    private Long maeId;
}
