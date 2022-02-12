package dev.trodrigues.order.services;

import dev.trodrigues.order.domain.Order;

public interface OrderService {

    Order saveOrder(Order order);

    Order findById(Long id);

}
