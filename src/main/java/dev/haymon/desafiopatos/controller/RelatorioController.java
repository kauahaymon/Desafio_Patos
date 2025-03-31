package dev.haymon.desafiopatos.controller;

import dev.haymon.desafiopatos.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping("/patos")
    public void gerarRelatorioPatos(HttpServletResponse response) throws IOException { ///  ex not able to generate

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio-patos.xlsx");

        Workbook arquivo = service.gerarRelatorioPatos();
        arquivo.write(response.getOutputStream());
        arquivo.close();
    }
}
