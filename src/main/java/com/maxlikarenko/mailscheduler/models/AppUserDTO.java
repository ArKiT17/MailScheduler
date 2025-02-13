package com.maxlikarenko.mailscheduler.models;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import jakarta.validation.constraints.Email;

public class AppUserDTO {
    private String username;
    @Email(message = "Невірний формат email")
    private String email;

    public AppUserDTO() {}

    public AppUserDTO(AppUser appUser) {
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
    }

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
