package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final EmailConfiguration emailConfiguration;

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfiguration.getUsername());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        emailSender.send(mailMessage);
    }
}
