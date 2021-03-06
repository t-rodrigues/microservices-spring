package dev.trodrigues.validatorworker.domain;

import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Order implements Serializable {

    private Long id;
    private String name;
    private String email;
    private Long product;
    private BigDecimal total;
    private LocalDate purchaseDate;
    private String cpfClient;
    private String cep;

    private Card card;

}
