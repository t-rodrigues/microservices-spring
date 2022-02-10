package dev.trodrigues.validatorworker.services;

import dev.trodrigues.validatorworker.domain.Order;

public interface ValidateService {

    void validateOrder(Order order);

}
