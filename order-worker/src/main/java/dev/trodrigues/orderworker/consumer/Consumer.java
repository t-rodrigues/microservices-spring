package dev.trodrigues.orderworker.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.trodrigues.orderworker.domain.Order;
import dev.trodrigues.orderworker.services.CepService;
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
    private final CepService cepService;

    @SneakyThrows
    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) {
        var order = objectMapper.readValue(message.getBody(), Order.class);
        cepService.findByCep(order.getCep());
        emailService.notifyClient(order);
        log.info("order: {}", order);
    }

}
