package ru.erulaev.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.erulaev.notification.config.RabbitMQConfig;
import ru.erulaev.notification.dto.DepositResponseDTO;

@Service
@AllArgsConstructor
public class DepositMessageHandler {

    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        String jsonBody = new String(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
        DepositResponseDTO depositResponseDTO = mapper.readValue(jsonBody, DepositResponseDTO.class);
        System.out.println(depositResponseDTO);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponseDTO.getEmail());
        mailMessage.setFrom("mail@mail.mail");

        mailMessage.setSubject("Deposit");
        mailMessage.setText("Make deposit, sum: " + depositResponseDTO.getAmount());

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
