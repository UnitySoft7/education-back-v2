package com.school.presence.student.core.exception;

import com.school.presence.student.core.dto.FieldsValidatorResponse;
import com.school.presence.student.core.dto.MessageResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalWebExceptionHandler {

    /**
     * Gère les erreurs de validation sur les @RequestBody annotés avec @Valid
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<FieldsValidatorResponse> handleValidationException(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new FieldsValidatorResponse(false, errors));
    }

    /**
     * Gère les erreurs de validation sur les @RequestParam, @PathVariable ou @Validated
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageResponse> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(false, e.getMessage()));
    }

    /**
     * Gère toutes les autres exceptions non capturées
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGenericException(Exception e) {
        // Log l'erreur si besoin (ajouter un logger)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(false, "Erreur serveur : " + e.getMessage()));
    }
}
