package dev.trodrigues.validatorworker.service;

import dev.trodrigues.validatorworker.domain.Order;

public interface ValidateService {

    void validateOrder(Order order) throws Exception;

}
