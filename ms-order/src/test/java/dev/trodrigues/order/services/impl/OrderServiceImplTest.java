package dev.trodrigues.order.services.impl;

import dev.trodrigues.order.domain.Order;
import dev.trodrigues.order.domain.OrderMock;
import dev.trodrigues.order.repositories.OrderRepository;
import dev.trodrigues.order.services.rabbitmq.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        var orderMock = OrderMock.creatOrder();

        when(orderRepository.save(any(Order.class))).thenReturn(orderMock);
        doNothing().when(producer).sendOrder(orderMock);

        var order = orderServiceImpl.saveOrder(orderMock);

        assertEquals(orderMock.getCep(), order.getCep());
        assertNotNull(order);
    }

}
