package dev.trodrigues.order.controllers.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private Integer status;
    private String error;
    private List<FieldErrorResponse> validationErrors;
    private String message;
    private String path;

}
