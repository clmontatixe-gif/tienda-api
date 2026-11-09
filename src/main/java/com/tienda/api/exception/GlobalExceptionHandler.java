package com.tienda.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejar productos que no existen
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarProductoNoEncontrado(
            ProductoNoEncontradoException ex) {

        Map<String, String> respuesta = Map.of(
                "error", ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }

    // Manejar productos con datos inválidos
    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<Map<String, String>> manejarProductoInvalido(
            ProductoInvalidoException ex) {

        Map<String, String> respuesta = Map.of(
                "error", ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
}
