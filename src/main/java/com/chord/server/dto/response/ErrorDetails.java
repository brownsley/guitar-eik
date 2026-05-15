package com.chord.server.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public ErrorDetails(String message, String detail) {
        this.message = message;
        this.details = detail;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorDetails(String message, String detail, Map<String, String> error) {
        this.message = message;
        this.details = detail;
        this.errors = error;
        this.timestamp = LocalDateTime.now();
    }

}
