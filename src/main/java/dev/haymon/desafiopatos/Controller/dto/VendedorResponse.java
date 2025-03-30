package dev.haymon.desafiopatos.Controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendedorResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String matricula;
}
