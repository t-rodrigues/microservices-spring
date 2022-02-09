package dev.trodrigues.orderworker.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.pending}")
    private String queuePending;

    @Bean
    public Queue queue() {
        return new Queue(queuePending, true);
    }

}
