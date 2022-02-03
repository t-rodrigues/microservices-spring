package dev.trodrigues.order.enums;

import org.springframework.http.HttpStatus;

public enum Errors {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request");

    private HttpStatus status;
    private String message;

    private Errors(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
