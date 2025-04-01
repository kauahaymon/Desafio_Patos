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
import java.util.stream.Collectors;

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

        List<Pato> todosPatos = patoRepository.findAll();
        List<Pato> patosMae = todosPatos.stream().filter(pato -> pato.getMae() == null).toList();

        int linhaIndex = 2;
        for (Pato mae : patosMae) {
            VendaPato vendaPatoMae = getVendaPato(mae);
            linhaIndex = formatter.adicionarLinha(planilha, mae, linhaIndex, vendaPatoMae, 0);

            List<Pato> filhos = filtrarPorMae(todosPatos, mae);
            for (Pato filho : filhos) {
                VendaPato vendaPatoFilho = getVendaPato(filho);
                linhaIndex = formatter.adicionarLinha(planilha, filho, linhaIndex, vendaPatoFilho, 1);

                List<Pato> netos = filtrarPorMae(todosPatos, filho);
                for (Pato neto : netos) {
                    VendaPato vendaPatoNeto = getVendaPato(neto);
                    linhaIndex = formatter.adicionarLinha(planilha, neto, linhaIndex, vendaPatoNeto, 2);
                }
            }
        }

        formatter.ajustarColunas(planilha);

        return arquivo;
    }

    private List<Pato> filtrarPorMae(List<Pato> todosPatos, Pato mae) {
        return todosPatos.stream()
                .filter(pato -> pato.getMae() != null && pato.getMae().getId().equals(mae.getId()))
                .collect(Collectors.toList());
    }

    private VendaPato getVendaPato(Pato mae) {
        return vendaPatoRepository.findByPatoId(mae.getId()).orElse(null);
    }
}
