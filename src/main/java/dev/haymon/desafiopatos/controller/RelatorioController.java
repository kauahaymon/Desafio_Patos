package dev.haymon.desafiopatos.controller;

import dev.haymon.desafiopatos.exception.RelatorioException;
import dev.haymon.desafiopatos.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping("/patos")
    public void gerarRelatorioPatos(HttpServletResponse response) {

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=relatorio-patos.xlsx");

            Workbook arquivo = service.gerarRelatorioPatos();
            arquivo.write(response.getOutputStream());
            arquivo.close();
            log.info("Um relatório foi gerado com sucesso");
        } catch (IOException e) {
            log.error("Erro ao gerar o relatório: {}", e.getMessage());
            throw new RelatorioException("Erro ao gerar o relatório. Tente novamente mais tarde.");
        }

    }
}
