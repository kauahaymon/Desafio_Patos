package dev.haymon.desafiopatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleErrosDeValidacao(MethodArgumentNotValidException e) {

        int code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        Set<String> erros = new HashSet<>();
        e.getBindingResult().getAllErrors()
                .forEach(erro -> erros.add(erro.getDefaultMessage()));

        return ResponseEntity
                .status(code)
                .body(ExceptionResponse.builder()
                        .status(code)
                        .message("Erro de validação")
                        .validationErrors(erros)
                        .build()
                );
    }

}
