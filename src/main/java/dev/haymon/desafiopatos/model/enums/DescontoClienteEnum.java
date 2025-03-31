package dev.haymon.desafiopatos.model.enums;

import lombok.Getter;

@Getter
public enum DescontoClienteEnum {

    DESCONTO_CLIENTE(0.8); // 20% de desconto

    private final Double valor;

    DescontoClienteEnum(Double valor) {
        this.valor = valor;
    }
}
