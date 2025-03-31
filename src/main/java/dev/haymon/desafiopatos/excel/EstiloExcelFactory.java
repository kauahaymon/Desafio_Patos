package dev.haymon.desafiopatos.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

@Component
public class EstiloExcelFactory {

    public CellStyle criarEstiloTitulo(Workbook arquivo) {
        CellStyle estilo = arquivo.createCellStyle();
        Font fonte = arquivo.createFont();
        fonte.setFontHeightInPoints((short) 14);
        fonte.setBold(true);
        estilo.setFont(fonte);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        return estilo;
    }

    public CellStyle criarEstiloCabecalho(Workbook arquivo) {
        CellStyle estilo = arquivo.createCellStyle();
        Font fonte = arquivo.createFont();
        fonte.setBold(true);
        estilo.setFont(fonte);
        estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }
}
