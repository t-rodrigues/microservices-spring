package dev.trodrigues.order.controllers.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.trodrigues.order.domain.Order;
import lombok.*;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(1)
    private Long product;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal total;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotBlank
    private String cpfClient;

    @NotBlank
    private String cep;

    public Order toOrder() {
        return new Order(null, name, email, product, total, purchaseDate, cpfClient, cep);
    }

}
