package dev.trodrigues.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {

    private String name;
    private Long product;
    private BigDecimal total;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;
    private String cpfClient;
    private String cep;

}
