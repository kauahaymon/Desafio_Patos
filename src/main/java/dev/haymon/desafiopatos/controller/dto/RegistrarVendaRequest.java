package dev.haymon.desafiopatos.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegistrarVendaRequest {

    @NotNull(message = "ID do Cliente não pode ser nulo")
    private Long clienteId;
    @NotNull(message = "ID do Vendedor não pode ser nulo")
    private Long vendedorId;
    @NotEmpty(message = "Informe ao menos um ID do pato para realizar a venda")
    private List<@NotNull(message = "ID do Pato não pode ser nulo") Long> patosIds;
}
