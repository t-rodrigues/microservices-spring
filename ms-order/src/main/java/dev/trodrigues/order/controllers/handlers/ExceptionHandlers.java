package dev.trodrigues.order.controllers.handlers;

import dev.trodrigues.order.controllers.responses.ErrorResponse;
import dev.trodrigues.order.controllers.responses.FieldErrorResponse;
import dev.trodrigues.order.enums.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        var validationErrors = ex.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResponse(fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList();
        return generateErrorResponse(ex, Errors.BAD_REQUEST, "Validation Error",
                request.getRequestURI(), validationErrors);
    }

    private ResponseEntity<ErrorResponse> generateErrorResponse(Exception ex, Errors errors,
            String message, String path, List<FieldErrorResponse> validation) {
        var errorResponse = ErrorResponse.builder().status(errors.getStatus().value())
                .error(ex.getClass().getSimpleName()).message(message).path(path)
                .validationErrors(validation).build();
        return ResponseEntity.status(errors.getStatus()).body(errorResponse);
    }

}
