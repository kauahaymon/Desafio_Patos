package dev.haymon.desafiopatos.service;

import dev.haymon.desafiopatos.excel.GeradorDePlanilhaDePatos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final GeradorDePlanilhaDePatos planilhaPatos;

    public Workbook gerarRelatorioPatos() {
        log.info("Gerando relatório de Patos...");
        return planilhaPatos.gerar();
    }
}
