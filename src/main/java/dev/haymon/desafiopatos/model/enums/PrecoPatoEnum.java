package dev.haymon.desafiopatos.model.enums;

import lombok.Getter;

@Getter
public enum PrecoPatoEnum {

    PRECO_SEM_FILHOS(70), // R$ 70 reais
    PRECO_COM_1_FILHO(50), // R$ 50 reais
    PRECO_COM_2_FILHOS(25) // R$ 25 reais
    ;

    private final double valor;

    PrecoPatoEnum(double valor) {
        this.valor = valor;
    }
}
