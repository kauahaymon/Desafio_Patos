package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.Controller.dto.PatoVendidoResponse;
import dev.haymon.desafiopatos.Controller.dto.RankingVendedoresResponse;
import dev.haymon.desafiopatos.Controller.dto.RegistrarVendaRequest;
import dev.haymon.desafiopatos.model.*;
import dev.haymon.desafiopatos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<PatoVendidoResponse> listarPatosVendidos() {
        List<VendaPato> vendas = vendaPatoRepository.findAll();

        List<PatoVendidoResponse> patosVendidos = new ArrayList<>();
        for (VendaPato vendaPato: vendas) {
            BigDecimal preco = vendaPato.getPrecoUnitario();
            Pato pato = vendaPato.getPato();
            Venda venda = vendaPato.getVenda();

            PatoVendidoResponse patoVendido = PatoVendidoResponse.builder()
                    .patoId(pato.getId())
                    .nomeDoPato(pato.getNome())
                    .precoUnitario(preco)
                    .nomeDoCliente(venda.getCliente().getNome())
                    .dataDaVenda(venda.getDataVenda())
                    .build();
            patosVendidos.add(patoVendido);
        }
        return patosVendidos;
    }

    public List<RankingVendedoresResponse> obterRankingVendedores(
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        LocalDateTime inicio = getInicioDoDia(dataInicio);
        LocalDateTime fim = getFimDoDia(dataFim);

        List<Venda> vendas = (inicio != null && fim != null)
                ? vendaRepository.findByDataVendaBetween(inicio, fim)
                : vendaRepository.findAll();

        Map<Long, RankingVendedoresResponse> vendedorMap = new HashMap<>();
        for (Venda venda: vendas) {
            Vendedor vendedor = venda.getVendedor();
            Long vendedorId = venda.getVendedor().getId();

            var vendedorResponse = RankingVendedoresResponse.builder()
                    .vendedorId(vendedorId)
                    .nome(vendedor.getNome())
                    .totalDeVendas(0)
                    .patosVendidos(0)
                    .valorTotalVendido(BigDecimal.ZERO)
                    .build();

            vendedorMap.putIfAbsent(vendedorId, vendedorResponse);
            var vendedorRanking = vendedorMap.get(vendedorId);

            BigDecimal valorVenda = venda.getValorTotal();
            int patosVendidos = venda.getPatos().size();

            vendedorRanking.setTotalDeVendas(vendedorRanking.getTotalDeVendas() + 1);
            vendedorRanking.setPatosVendidos(vendedorRanking.getPatosVendidos() + patosVendidos);
            vendedorRanking.setValorTotalVendido(vendedorRanking.getValorTotalVendido().add(valorVenda));
        }

        List<RankingVendedoresResponse> vendedores = new ArrayList<>(vendedorMap.values());
        vendedores.sort((
                (o1, o2) -> o2.getValorTotalVendido()
                .compareTo(o1.getValorTotalVendido())
        ));

        for (int i = 0; i < vendedores.size(); i++) {
            vendedores.get(i).setLugar(i + 1);
        }
        return vendedores;
    }

    private static LocalDateTime getFimDoDia(LocalDate dataFim) {
        return dataFim != null ? dataFim.atTime(23, 59, 59) : null;
    }

    private static LocalDateTime getInicioDoDia(LocalDate dataInicio) {
        return dataInicio != null ? dataInicio.atStartOfDay() : null;
    }
}
