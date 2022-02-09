package dev.trodrigues.orderworker.services;

import dev.trodrigues.orderworker.domain.Order;

public interface EmailService {
    void notifyClient(Order order);
}
