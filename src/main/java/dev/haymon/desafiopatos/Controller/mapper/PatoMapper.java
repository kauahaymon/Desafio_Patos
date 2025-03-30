package dev.haymon.desafiopatos.Controller.mapper;

import dev.haymon.desafiopatos.Controller.dto.PatoResponse;
import dev.haymon.desafiopatos.model.Pato;
import org.springframework.stereotype.Component;

@Component
public class PatoMapper {

    public PatoResponse toDTO(Pato pato) {

        Long maeId = (pato.getMae() != null) ? pato.getMae().getId() : null;

        return PatoResponse
                .builder()
                .id(pato.getId())
                .nome(pato.getNome())
                .vendido(pato.isVendido())
                .maeId(maeId)
                .build();
    }
}
