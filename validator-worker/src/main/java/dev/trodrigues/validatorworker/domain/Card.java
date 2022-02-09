package dev.trodrigues.validatorworker.domain;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Card {

    private String number;
    private BigDecimal limitAvailable;

}
