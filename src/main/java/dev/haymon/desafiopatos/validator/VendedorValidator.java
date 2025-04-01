package dev.haymon.desafiopatos.validator;

import dev.haymon.desafiopatos.exception.RegistroDuplicadoException;
import dev.haymon.desafiopatos.model.Vendedor;
import dev.haymon.desafiopatos.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendedorValidator {

    private final VendedorRepository vendedorRepository;

    public void validar(Vendedor vendedor) {

        if (vendedorRepository.existsByCpf(vendedor.getCpf())) {
            throw new RegistroDuplicadoException("Já existe um Vendedor com esse CPF");
        }

        if (vendedorRepository.existsByMatricula(vendedor.getMatricula())) {
            throw new RegistroDuplicadoException("Já existe um Vendedor com essa Matrícula");
        }
    }
}
