package dev.trodrigues.validatorworker.services.exceptions;

public class UnavailableLimitException extends RuntimeException {

    public UnavailableLimitException(String message) {
        super(message);
    }

}
