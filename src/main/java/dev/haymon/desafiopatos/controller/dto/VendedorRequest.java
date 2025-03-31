package dev.haymon.desafiopatos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Builder
public class VendedorRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    @CPF(message = "CPF inválido")
    @NotBlank(message = "Campo CPF é obrigatório")
    private String cpf;
    @NotBlank(message = "Campo matrícula é obrigatório")
    private String matricula;
}
