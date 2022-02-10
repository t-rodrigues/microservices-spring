package dev.trodrigues.validatorworker.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.trodrigues.validatorworker.domain.Order;
import dev.trodrigues.validatorworker.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final ObjectMapper objectMapper;
    private final ValidateService validateService;

    @RabbitListener(queues = "${queue.name}")
    public void consumer(@Payload Message message) throws IOException {
        var order = objectMapper.readValue(message.getBody(), Order.class);
        log.info("Validator Worker - Order: {}", order);

        try {
            validateService.validateOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
