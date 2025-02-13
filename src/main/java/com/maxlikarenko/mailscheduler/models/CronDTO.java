package com.maxlikarenko.mailscheduler.models;

import com.maxlikarenko.mailscheduler.entities.Cron;
import jakarta.validation.constraints.NotBlank;

public class CronDTO {
    @NotBlank(message = "Expression не може бути пустим")
    private String expression;

    public CronDTO() {}

    public CronDTO(Cron cron) {
        this.expression = cron.getExpression();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
