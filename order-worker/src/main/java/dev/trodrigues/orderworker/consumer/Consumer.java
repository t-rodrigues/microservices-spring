package dev.trodrigues.orderworker.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.trodrigues.orderworker.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) {
        var order = objectMapper.readValue(message.getBody(), Order.class);
        System.out.println(order);
    }

}
