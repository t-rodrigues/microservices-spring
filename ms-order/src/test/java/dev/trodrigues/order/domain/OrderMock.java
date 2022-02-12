package dev.trodrigues.order.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class OrderMock {

    public static Order creatOrder() {
        return Order.builder()
                .id(new Random().nextLong())
                .name(UUID.randomUUID().toString())
                .email(UUID.randomUUID() + "@main.com")
                .product(new Random().nextLong())
                .total(BigDecimal.valueOf(new Random().nextDouble() * 100))
                .purchaseDate(LocalDate.parse("2022-02-01"))
                .cpfClient(UUID.randomUUID().toString())
                .cep(UUID.randomUUID().toString())
                .build();
    }

}
