package dev.trodrigues.orderworker.services.impl;

import dev.trodrigues.orderworker.domain.Order;
import dev.trodrigues.orderworker.producers.OrderProducer;
import dev.trodrigues.orderworker.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final OrderProducer orderProducer;

    @Value("${mail.from}")
    private String from;

    @Override
    public void notifyClient(Order order) {
        var msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(String.format("%s <%s>", order.getName(), order.getEmail()));
        msg.setSubject("Pedido de compra recebido");
        msg.setText(
                """
                        Este é um e-mail de confirmação de compra recebida.

                        Agora vamos aprovar sua compra e brevemente você receberá um novo e-mail de confirmação

                        Obrigado por comprar com a gente!!
                                """);
        javaMailSender.send(msg);
        log.info("Notify sended");

        orderProducer.sendOrder(order);
        log.info("OrderProducer");
    }

}
