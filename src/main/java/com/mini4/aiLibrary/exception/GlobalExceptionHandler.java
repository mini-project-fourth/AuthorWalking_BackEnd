package com.mini4.aiLibrary.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 404);
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        error.put("timestamp", LocalDateTime.now());
        return error;
    }
}
