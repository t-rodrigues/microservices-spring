package dev.trodrigues.order.services.impl;

import dev.trodrigues.order.domain.Order;
import dev.trodrigues.order.repositories.OrderRepository;
import dev.trodrigues.order.services.rabbitmq.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Producer producer;

    @Test
    void shouldSaveOrderWhenSuccessful() {
        var orderMok = getOrder();

        var order = orderServiceImpl.saveOrder(orderMok);

        assertEquals(orderMok.getCep(), order.getCep());
    }

    Order getOrder() {
        return Order.builder()
                .name("John Doe")
                .email("john@doe.com")
                .product(1L)
                .total(BigDecimal.TEN)
                .purchaseDate(LocalDate.now())
                .cpfClient("12312")
                .cep("99000999")
                .build();
    }

}
