package dev.trodrigues.orderworker.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.trodrigues.orderworker.domain.Card;
import dev.trodrigues.orderworker.domain.Order;
import dev.trodrigues.orderworker.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper objectMapper;
    private final CardService cardService;

    @SneakyThrows
    @PostMapping
    public void sendOrder(Order order) {
        order.setCard(Card.builder().number(cardService.generateCard())
                .limitAvailable(cardService.generateCardLimit()).build());

        rabbitTemplate.convertAndSend(queue.getName(), objectMapper.writeValueAsString(order));
        log.info("OrderProducer: {}", order);
    }

}
