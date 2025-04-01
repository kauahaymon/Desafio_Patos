package dev.haymon.desafiopatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ExceptionResponse> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e) {
        int status = HttpStatus.NOT_FOUND.value();

        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message(e.getMessage())
                        .validationErrors(new HashSet<>())
                        .build());
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ExceptionResponse> handleRegistroDuplicado(RegistroDuplicadoException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message(e.getMessage())
                        .validationErrors(new HashSet<>())
                        .build());
    }

    @ExceptionHandler(PatosNaoEncontradosException.class)
    public ResponseEntity<ExceptionResponse> handlePatosNaoEncontrados(PatosNaoEncontradosException e) {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message(e.getMessage())
                        .validationErrors(new HashSet<>())
                        .build());
    }

    @ExceptionHandler(PatoVendidoException.class)
    public ResponseEntity<ExceptionResponse> handlePatoVendido(PatoVendidoException e) {
        int status = HttpStatus.CONFLICT.value();
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message(e.getMessage())
                        .validationErrors(new HashSet<>())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationErrors(MethodArgumentNotValidException e) {
        int status = HttpStatus.UNPROCESSABLE_ENTITY.value();
        Set<String> erros = new HashSet<>();
        e.getBindingResult().getAllErrors()
                .forEach(erro -> erros.add(erro.getDefaultMessage()));
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message("Erro de validação")
                        .validationErrors(erros)
                        .build()
                );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        e.printStackTrace();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status)
                        .message("Ocorreu um erro inesperado. Tente novamente mais tarde")
                        .validationErrors(new HashSet<>())
                        .build()
                );
    }
}
