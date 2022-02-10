package dev.trodrigues.validatorworker.service.impl;

import dev.trodrigues.validatorworker.domain.Card;
import dev.trodrigues.validatorworker.domain.Order;
import dev.trodrigues.validatorworker.service.ValidateService;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Override
    public void validateOrder(Order order) throws Exception {
        validateAvailableLimit(order.getCard());
        validateOrderWithLimit(order);
    }

    private void validateOrderWithLimit(Order order) throws Exception {
        if (order.getTotal().longValue() > order.getCard().getLimitAvailable().longValue())
            throw new Exception("Insufficient funds");
    }

    private void validateAvailableLimit(Card card) throws Exception {
        if (card.getLimitAvailable().longValue() <= 0)
            throw new Exception("Limit unavailable");
    }

}
