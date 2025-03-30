package dev.haymon.desafiopatos.Controller.mapper;

import dev.haymon.desafiopatos.Controller.dto.VendedorResponse;
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
