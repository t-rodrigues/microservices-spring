package dev.trodrigues.order.services.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @PostMapping
    public void producer(@RequestBody String payload) {
        rabbitTemplate.convertAndSend(queue.getName(), payload);
    }

}
