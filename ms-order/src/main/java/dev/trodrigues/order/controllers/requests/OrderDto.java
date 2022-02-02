package dev.trodrigues.order.controllers.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.trodrigues.order.domain.Order;
import lombok.Getter;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class OrderDto {

    @NotBlank
    private String name;

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
        return new Order(null, name, product, total, purchaseDate, cpfClient, cep);
    }

}
