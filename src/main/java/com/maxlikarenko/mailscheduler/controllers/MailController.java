package com.maxlikarenko.mailscheduler.controllers;

import com.maxlikarenko.mailscheduler.LogType;
import com.maxlikarenko.mailscheduler.services.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> sendMail(@PathVariable int userId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("sent", mailService.sendInfoByUserId(userId, LogType.REST));
        return ResponseEntity.ok(response);
    }
}
