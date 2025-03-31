package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.Controller.dto.RegistrarVendaRequest;
import dev.haymon.desafiopatos.model.*;
import dev.haymon.desafiopatos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dev.haymon.desafiopatos.model.enums.DescontoClienteEnum.DESCONTO_CLIENTE;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final PatoRepository patoRepository;
    private final VendaRepository vendaRepository;
    private final VendaPatoRepository vendaPatoRepository;

    @Transactional
    public void registrar(RegistrarVendaRequest dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(); /// ex not found
        Vendedor vendedor = vendedorRepository.findById(dto.getVendedorId())
                .orElseThrow(); /// ex not found

        List<Pato> patos = patoRepository.findAllById(dto.getPatosIds());
        if (patos.size() != dto.getPatosIds().size()) {
            throw new RuntimeException("Um ou mais patos não foram encontrados");
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<VendaPato> vendaPatos = new ArrayList<>();

        Venda novaVenda = Venda.builder()
                .cliente(cliente)
                .vendedor(vendedor)
                .valorTotal(valorTotal)
                .build();
        vendaRepository.save(novaVenda);

        for (Pato pato: patos) {
            if (pato.isVendido()) {
                throw new IllegalStateException("O pato com id " + pato.getId() + " já foi vendido");
            }

            BigDecimal preco = pato.calcularPreco();
            valorTotal = valorTotal.add(preco);
            VendaPato vendaPato = VendaPato.builder()
                    .pato(pato)
                    .precoUnitario(preco)
                    .venda(novaVenda)
                    .build();
            pato.setVendido(true);
            vendaPatos.add(vendaPato);
            vendaPatoRepository.save(vendaPato);
        }

        if (cliente.isElegivelParaDesconto()) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(DESCONTO_CLIENTE.getValor()));
        }

        novaVenda.setValorTotal(valorTotal);
        novaVenda.setPatos(vendaPatos);

        patoRepository.saveAll(patos);
        vendaRepository.save(novaVenda);
    }
}
