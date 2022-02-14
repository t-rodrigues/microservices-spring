package dev.trodrigues.order.services.impl;

import dev.trodrigues.order.domain.Order;
import dev.trodrigues.order.domain.OrderMock;
import dev.trodrigues.order.repositories.OrderRepository;
import dev.trodrigues.order.services.exceptions.ObjectNotFoundException;
import dev.trodrigues.order.services.rabbitmq.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Producer producer;

    @Test
    void shouldSaveOrderWhenSuccessful() {
        var orderMock = OrderMock.creatOrder();

        when(orderRepository.save(any(Order.class))).thenReturn(orderMock);
        doNothing().when(producer).sendOrder(orderMock);

        var order = orderService.saveOrder(orderMock);

        assertEquals(orderMock.getCep(), order.getCep());
        assertNotNull(order);
    }

    @Test
    void shouldThrowWhenNonExistingId() {
        var nonExistingId = 109L;

        when(orderRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> orderService.findById(nonExistingId));
    }

    @Test
    void shouldReturnOrderWhenExistingId() {
        var orderMock = OrderMock.creatOrder();
        var existingId = orderMock.getId();

        when(orderRepository.findById(existingId)).thenReturn(Optional.of(orderMock));

        var order = orderService.findById(existingId);

        assertEquals(existingId, order.getId());
        assertNotNull(order);
        verify(orderRepository, times(1)).findById(existingId);
    }

    @Test
    void shouldDeleteOrderSuccessful() {
        var orderMock = OrderMock.creatOrder();
        var existingId = orderMock.getId();

        when(orderRepository.findById(existingId)).thenReturn(Optional.of(orderMock));
        doNothing().when(orderRepository).delete(orderMock);

        assertDoesNotThrow(() -> orderService.delete(existingId));
        verify(orderRepository, times(1)).delete(orderMock);
    }

    @Test
    void shouldThrowWhenNonExistingIdWasProvided() {
        var nonExistingId = 6L;

        when(orderRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> orderService.delete(nonExistingId));
        verify(orderRepository, times(0)).delete(any(Order.class));
    }

}
