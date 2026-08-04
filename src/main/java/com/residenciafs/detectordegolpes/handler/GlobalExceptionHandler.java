package com.residenciafs.detectordegolpes.handler;

import com.residenciafs.detectordegolpes.dto.ErroResponse;
import com.residenciafs.detectordegolpes.exception.APIKeyInvalid;
import com.residenciafs.detectordegolpes.exception.GeminiException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIKeyInvalid.class)
    public ResponseEntity<ErroResponse> handleAPIKeyInvalid(APIKeyInvalid e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponse("Chave de API inválida", e.getMessage()));
    }

    @ExceptionHandler(GeminiException.class)
    public ResponseEntity<ErroResponse> handleGeminiException(GeminiException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponse("Erro de comunicação com o Gemini", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String detalhe = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse("Campos faltando",
                        detalhe.isBlank() ? "Erro de validação" : detalhe));
    }
}
