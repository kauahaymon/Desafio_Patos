package dev.haymon.desafiopatos.excel;

import dev.haymon.desafiopatos.model.Pato;
import dev.haymon.desafiopatos.model.VendaPato;
import dev.haymon.desafiopatos.repository.PatoRepository;
import dev.haymon.desafiopatos.repository.VendaPatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeradorDePlanilhaDePatos {

    private final EstiloExcelFactory estiloFactory;
    private final DadosPatosFormatter formatter;
    private final PatoRepository patoRepository;
    private final VendaPatoRepository vendaPatoRepository;

    public Workbook gerar() {
        Workbook arquivo = new XSSFWorkbook();
        Sheet planilha = arquivo.createSheet("Relatório de Patos");

        CellStyle estiloTitulo = estiloFactory.criarEstiloTitulo(arquivo);
        CellStyle estiloCabecalho = estiloFactory.criarEstiloCabecalho(arquivo);

        formatter.adicionarTitulo(planilha, estiloTitulo);
        formatter.adicionarCabecalho(planilha, estiloCabecalho);

        List<Pato> patosMae = patoRepository.findByMaeIsNullOrderByNomeAsc();

        int linhaIndex = 2;
        for (Pato mae : patosMae) {
            VendaPato vendaPatoMae = vendaPatoRepository.findByPatoId(mae.getId()).orElse(null);
            linhaIndex = formatter.adicionarLinha(planilha, mae, linhaIndex, vendaPatoMae);

            List<Pato> filhos = patoRepository.findByMaeIdOrderByNomeAsc(mae.getId());
            for (Pato filho : filhos) {
                VendaPato vendaPatoFilho = vendaPatoRepository.findByPatoId(filho.getId()).orElse(null);
                linhaIndex = formatter.adicionarLinha(planilha, filho, linhaIndex, vendaPatoFilho);
            }
        }
        formatter.ajustarColunas(planilha);

        return arquivo;
    }
}
