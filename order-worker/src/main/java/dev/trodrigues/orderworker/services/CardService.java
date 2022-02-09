package dev.trodrigues.orderworker.services;

import java.math.BigDecimal;

public interface CardService {

    String generateCard();

    BigDecimal generateCardLimit();

}
