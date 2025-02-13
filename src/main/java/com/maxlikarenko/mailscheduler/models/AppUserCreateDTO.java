package com.maxlikarenko.mailscheduler.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AppUserCreateDTO {
    @NotBlank(message = "Username не може бути пустим")
    private String username;
    @NotBlank(message = "Email не може бути пустим")
    @Email(message = "Невірний формат email")
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
