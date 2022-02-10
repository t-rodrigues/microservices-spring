package dev.trodrigues.validatorworker.services.impl;

import dev.trodrigues.validatorworker.domain.Card;
import dev.trodrigues.validatorworker.domain.Order;
import dev.trodrigues.validatorworker.services.EmailService;
import dev.trodrigues.validatorworker.services.ValidateService;
import dev.trodrigues.validatorworker.services.exceptions.InsufficientFundsException;
import dev.trodrigues.validatorworker.services.exceptions.UnavailableLimitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final EmailService emailService;

    @Override
    public void validateOrder(Order order) {
        validateAvailableLimit(order.getCard());
        validateOrderWithLimit(order);

        emailService.notifyClient(String.format("%s <%s>", order.getName(), order.getEmail()));
        log.info("Notify sended, Order approved: {}", order);
    }

    private void validateOrderWithLimit(Order order) {
        if (order.getTotal().longValue() > order.getCard().getLimitAvailable().longValue()) {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }

    private void validateAvailableLimit(Card card) {
        if (card.getLimitAvailable().longValue() <= 0) {
            throw new UnavailableLimitException("Limit unavailable");
        }
    }

}
