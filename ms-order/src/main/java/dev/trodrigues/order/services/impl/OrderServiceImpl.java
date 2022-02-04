package dev.trodrigues.order.services.impl;

import dev.trodrigues.order.domain.Order;
import dev.trodrigues.order.repositories.OrderRepository;
import dev.trodrigues.order.services.OrderService;
import dev.trodrigues.order.services.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Producer producer;

    @Override
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        producer.sendOrder(order);
        return order;
    }

}
