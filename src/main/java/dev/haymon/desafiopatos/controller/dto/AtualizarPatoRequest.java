package dev.haymon.desafiopatos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AtualizarPatoRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    private boolean vendido;
}
