package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.model.Cliente;
import dev.haymon.desafiopatos.model.Pato;
import dev.haymon.desafiopatos.model.Venda;
import dev.haymon.desafiopatos.model.VendaPato;
import dev.haymon.desafiopatos.repository.PatoRepository;
import dev.haymon.desafiopatos.repository.VendaPatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final PatoRepository patoRepository;
    private final VendaPatoRepository vendaPatoRepository;

    public Workbook gerarRelatorioPatos() {
        Workbook arquivo = new XSSFWorkbook();

        String nomePlanilha = "Relatório de Patos";
        Sheet planilha = arquivo.createSheet(nomePlanilha);
        CellStyle estiloCabecalho = gerarEstiloCabecalho(arquivo);

        Row linhaTitulo = planilha.createRow(0);
        Cell celulaTitulo = linhaTitulo.createCell(0);
        celulaTitulo.setCellValue("GERENCIAMENTO DE PATOS");

        planilha.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        CellStyle estiloTitulo = arquivo.createCellStyle();
        Font fonteTitulo = arquivo.createFont();
        fonteTitulo.setFontHeightInPoints((short) 14); // tamanho da fonte
        fonteTitulo.setBold(true);
        estiloTitulo.setFont(fonteTitulo);
        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);
        celulaTitulo.setCellStyle(estiloTitulo);


        String[] titulos = {"Nome do Pato", "Status", "Cliente", "Tipo do Cliente", "Valor"};
        gerarCabecalho(planilha, estiloCabecalho, titulos);

        List<Pato> patosMae = patoRepository.findByMaeIsNullOrderByNomeAsc();

        int linhaIndex = 2;

        for (Pato patoMae : patosMae) {
            linhaIndex = adicionarLinhaPato(planilha, patoMae, linhaIndex);

            List<Pato> filhos = patoRepository.findByMaeIdOrderByNomeAsc(patoMae.getId());

            for (Pato filho: filhos) {
                linhaIndex = adicionarLinhaPato(planilha, filho, linhaIndex);
            }
        }

        planilha.autoSizeColumn(0);
        int largura = planilha.getColumnWidth(0);

        planilha.setColumnWidth(0, largura + 2000);
        planilha.setColumnWidth(1, 5000);
        planilha.setColumnWidth(2, 7000);
        planilha.setColumnWidth(3, 7000);
        planilha.setColumnWidth(4, 4000);

        return arquivo;
    }

    private int adicionarLinhaPato(Sheet planilha, Pato pato, int linhaIndex) {
        Row linha = planilha.createRow(linhaIndex++);
        linha.createCell(0).setCellValue(gerarNomeIdentado(pato));

        boolean vendido = pato.isVendido();
        linha.createCell(1).setCellValue(vendido ? "Indisponível" : "Disponível");

        if (vendido) {
            VendaPato vendaPato = vendaPatoRepository.findByPatoId(pato.getId()).orElseThrow();
            Venda venda = vendaPato.getVenda();
            Cliente cliente = venda.getCliente();

            String clienteNome = cliente.getNome();
            String tipoDoCliente = cliente.isElegivelParaDesconto() ? "Com desconto" : "Sem desconto";
            BigDecimal precoUnitario = vendaPato.getPrecoUnitario();

            linha.createCell(2).setCellValue(clienteNome);
            linha.createCell(3).setCellValue(tipoDoCliente);
            linha.createCell(4).setCellValue("R$ " + precoUnitario.toString());
        } else {
            linha.createCell(2).setCellValue("-");
            linha.createCell(3).setCellValue("-");
            linha.createCell(4).setCellValue("-");
        }

        return linhaIndex;
    }

    private static void gerarCabecalho(Sheet planilha, CellStyle estiloCabecalho, String[] titulos ) {
        Row cabecalho = planilha.createRow(1);

        for (int i = 0; i < titulos.length; i++) {
            Cell celula = cabecalho.createCell(i);
            celula.setCellValue(titulos[i]);
            celula.setCellStyle(estiloCabecalho);
        }
    }

    private static CellStyle gerarEstiloCabecalho(Workbook arquivo) {
        CellStyle estiloCabecalho = arquivo.createCellStyle();
        Font fonte = arquivo.createFont();
        fonte.setBold(true);
        estiloCabecalho.setFont(fonte);
        estiloCabecalho.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        estiloCabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estiloCabecalho;
    }

    private String gerarNomeIdentado(Pato pato) {
        int nivel = 0;
        Pato atual = pato.getMae();
        while (atual != null) {
            nivel++;
            atual = atual.getMae();
        }
        return "   ".repeat(nivel) + pato.getNome();
    }
}
