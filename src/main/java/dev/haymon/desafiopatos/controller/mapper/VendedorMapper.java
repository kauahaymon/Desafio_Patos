package dev.haymon.desafiopatos.controller.mapper;

import dev.haymon.desafiopatos.controller.dto.VendedorResponse;
import dev.haymon.desafiopatos.model.Vendedor;
import org.springframework.stereotype.Component;

@Component
public class VendedorMapper {

    public VendedorResponse toDTO(Vendedor vendedor) {
        return VendedorResponse
                .builder()
                .id(vendedor.getId())
                .nome(vendedor.getNome())
                .cpf(vendedor.getCpf())
                .matricula(vendedor.getMatricula())
                .build();
    }
}
