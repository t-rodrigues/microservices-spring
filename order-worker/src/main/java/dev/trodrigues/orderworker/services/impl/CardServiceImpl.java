package dev.trodrigues.orderworker.services.impl;

import dev.trodrigues.orderworker.services.CardService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CardServiceImpl implements CardService {

    @Override
    public String generateCard() {
        var seq1 = BigDecimal.valueOf(1000 + (long) (Math.random() * 500));
        var seq2 = BigDecimal.valueOf(2000 + (long) (Math.random() * 500));
        var seq3 = BigDecimal.valueOf(3000 + (long) (Math.random() * 500));
        var seq4 = BigDecimal.valueOf(4000 + (long) (Math.random() * 500));
        return String.format("%s %s %s %s", seq1, seq2, seq3, seq4);
    }

    @Override
    public BigDecimal generateCardLimit() {
        return BigDecimal.valueOf(1000 + (long) (Math.random() * 500)).setScale(2,
                RoundingMode.HALF_UP);
    }

}
