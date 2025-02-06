package com.maxlikarenko.mailscheduler.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public class AppUserUpdateDTO {
    @Column(nullable = false)
    private String username;
    @Email(message = "Невірний формат email")
    @Column(nullable = false)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
