package dev.trodrigues.validatorworker.services.impl;

import dev.trodrigues.validatorworker.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${mail.from}")
    private String from;

    @Override
    public void notifyClient(String email) {
        var msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(email);
        msg.setSubject("Pedido de compra aprovada");
        msg.setText(
                """
                        Sua compra foi aprovada!

                        Em breve você receberá o código de rastreio.

                        Obrigado pela preferência.
                                """);
        javaMailSender.send(msg);
    }

}
