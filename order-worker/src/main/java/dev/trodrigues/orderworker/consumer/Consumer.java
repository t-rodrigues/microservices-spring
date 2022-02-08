package dev.trodrigues.orderworker.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.trodrigues.orderworker.domain.Order;
import dev.trodrigues.orderworker.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @SneakyThrows
    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) {
        var order = objectMapper.readValue(message.getBody(), Order.class);
        log.info("order: {}", order);
        emailService.notifyClient(String.format("%s <%s>", order.getName(), order.getEmail()));
    }

}
