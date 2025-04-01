package dev.haymon.desafiopatos.excel;

import dev.haymon.desafiopatos.model.Cliente;
import dev.haymon.desafiopatos.model.Pato;
import dev.haymon.desafiopatos.model.Venda;
import dev.haymon.desafiopatos.model.VendaPato;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DadosPatosFormatter {

    public void adicionarTitulo(Sheet planilha, CellStyle estilo) {
        Row linhaTitulo = planilha.createRow(0);
        Cell celulaTitulo = linhaTitulo.createCell(0);
        celulaTitulo.setCellValue("GERENCIAMENTO DE PATOS");
        celulaTitulo.setCellStyle(estilo);
        planilha.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
    }

    public void adicionarCabecalho(Sheet planilha, CellStyle estilo) {
        String[] titulos = {"Nome do Pato", "Status", "Cliente", "Tipo do Cliente", "Valor"};
        Row cabecalho = planilha.createRow(1);
        for (int i = 0; i < titulos.length; i++) {
            Cell celula = cabecalho.createCell(i);
            celula.setCellValue(titulos[i]);
            celula.setCellStyle(estilo);
        }
    }

    public int adicionarLinha(
            Sheet planilha,
            Pato pato,
            int linhaIndex,
            VendaPato vendaPato,
            int nivel
    ) {
        Row linha = planilha.createRow(linhaIndex++);

        linha.createCell(0).setCellValue(gerarNomeIdentado(pato, nivel));

        boolean vendido = pato.isVendido();
        linha.createCell(1).setCellValue(vendido ? "Indisponível" : "Disponível");

        if (vendido) {
            Venda venda = vendaPato.getVenda();
            Cliente cliente = venda.getCliente();

            linha.createCell(2).setCellValue(cliente.getNome());
            linha.createCell(3).setCellValue(cliente.isElegivelParaDesconto() ? "Com desconto" : "Sem desconto");
            linha.createCell(4).setCellValue("R$ " + vendaPato.getPrecoUnitario().toString());
        } else {
            linha.createCell(2).setCellValue("-");
            linha.createCell(3).setCellValue("-");
            linha.createCell(4).setCellValue("-");
        }

        return linhaIndex;
    }

    public void ajustarColunas(Sheet planilha) {
        planilha.autoSizeColumn(0);
        int largura = planilha.getColumnWidth(0);
        planilha.setColumnWidth(0, largura + 2000);
        planilha.setColumnWidth(1, 5000);
        planilha.setColumnWidth(2, 7000);
        planilha.setColumnWidth(3, 7000);
        planilha.setColumnWidth(4, 4000);
    }

    private String gerarNomeIdentado(Pato pato, int nivel) {
        return "   ".repeat(nivel) + pato.getNome();
    }
}
