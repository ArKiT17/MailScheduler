package com.maxlikarenko.mailscheduler.models;

import com.maxlikarenko.mailscheduler.entities.Cron;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CronDTO {
    @NotBlank(message = "Expression не може бути пустим")
    private String expression;
    private LocalDateTime createdOn;

    public CronDTO() {}

    public CronDTO(Cron cron) {
        this.expression = cron.getExpression();
        this.createdOn = cron.getCreatedOn();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
