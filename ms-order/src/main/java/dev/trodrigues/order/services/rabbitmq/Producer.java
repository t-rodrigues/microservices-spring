package dev.trodrigues.order.services.rabbitmq;

import dev.trodrigues.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @PostMapping
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(queue.getName(), order);
    }

}
