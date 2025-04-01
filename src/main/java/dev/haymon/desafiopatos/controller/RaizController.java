package dev.haymon.desafiopatos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaizController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String mensagem = "Bem-vindo à API! 🚀 Teste os endpoints via Postman: "
                + "<a href='https://github.com/kauahaymon/Desafio_Patos/blob/main/testes/postman/Pato%27s.postman_collection.json' target='_blank'>Baixar JSON</a>";

        return ResponseEntity.ok().body(mensagem);
    }
}
