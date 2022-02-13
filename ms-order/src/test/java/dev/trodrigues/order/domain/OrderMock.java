package dev.trodrigues.order.domain;

import dev.trodrigues.order.controllers.requests.OrderDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class OrderMock {

    public static Order creatOrder() {
        return Order.builder()
                .id(new Random().nextLong())
                .name(UUID.randomUUID().toString())
                .email(UUID.randomUUID() + "@mail.com")
                .product(new Random().nextLong())
                .total(BigDecimal.valueOf(new Random().nextDouble() * 100))
                .purchaseDate(LocalDate.parse("2022-02-01"))
                .cpfClient(UUID.randomUUID().toString())
                .cep(UUID.randomUUID().toString())
                .build();
    }

    public static OrderDto createOrderRequest() {
        return OrderDto.builder()
                .name(UUID.randomUUID().toString())
                .email(UUID.randomUUID().toString() + "@mail.com")
                .product(new Random().nextLong(100))
                .total(BigDecimal.valueOf(new Random().nextDouble() * 100).setScale(2, RoundingMode.HALF_UP))
                .purchaseDate(LocalDate.now())
                .cpfClient(UUID.randomUUID().toString().substring(0, 19))
                .cep(UUID.randomUUID().toString().substring(0, 8))
                .build();
    }

}
