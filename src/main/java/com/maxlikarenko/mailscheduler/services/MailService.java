package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.LogType;
import com.maxlikarenko.mailscheduler.entities.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final AppUserService appUserService;
    private final AppLogService appLogService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public MailService(JavaMailSender mailSender, AppUserService appUserService, AppLogService appLogService) {
        this.mailSender = mailSender;
        this.appUserService = appUserService;
        this.appLogService = appLogService;
    }

    private void send(String emailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public boolean sendInfoByUserId(int userId, LogType logType) {
        AppUser user = appUserService.findUserById(userId);
        send(
                user.getEmail(),
                "Вітання!",
                String.format("Імʼя користувача: %s\nДата та час створення: %s", user.getUsername(), user.getCreatedOn())
        );
        appLogService.addLog(user, logType);
        return true;
    }

    public void sendInfoToAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        for (AppUser user : users)
            sendInfoByUserId(user.getId(), LogType.CRON);
    }
}
