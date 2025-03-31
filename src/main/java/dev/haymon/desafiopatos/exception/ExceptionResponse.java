package dev.haymon.desafiopatos.exception;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private Integer status;
    private String message;
    private Set<String> validationErrors;
}
