package dev.trodrigues.order.services.rabbitmq;

import dev.trodrigues.order.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Order order) {
        System.out.println(order);
    }

}
