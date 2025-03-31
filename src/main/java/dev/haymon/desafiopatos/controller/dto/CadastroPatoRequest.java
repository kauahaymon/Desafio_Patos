package dev.haymon.desafiopatos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CadastroPatoRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    @Positive(message = "ID da mãe deve ser positivo")
    private Long maeId;
}
