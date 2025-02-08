package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final AppUserService appUserService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public MailService(JavaMailSender mailSender, AppUserService appUserService) {
        this.mailSender = mailSender;
        this.appUserService = appUserService;
    }

    private void send(String emailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public boolean sendInfoByUserId(int userId) {
        AppUser user = appUserService.findUserById(userId);
        send(
                user.getEmail(),
                "Вітання!",
                String.format("Імʼя користувача: %s\nДата та час створення: %s", user.getUsername(), user.getCreatedOn())
        );
        return true;
    }
}
