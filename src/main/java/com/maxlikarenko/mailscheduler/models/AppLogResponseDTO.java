package com.maxlikarenko.mailscheduler.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogResponseDTO {
    private String username;
    private String email;
    private MailCount count;
    private String first;
    private String last;

    public AppLogResponseDTO(String username, String email, Long restCount, Long cronCount,
                             LocalDateTime firstMail, LocalDateTime lastMail) {
        this.username = username;
        this.email = email;
        this.count = new MailCount(restCount.intValue(), cronCount.intValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.first = firstMail.format(formatter);
        this.last = lastMail.format(formatter);
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

    public MailCount getCount() {
        return count;
    }

    public void setCount(MailCount count) {
        this.count = count;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public static class MailCount {
        private int rest;
        private int cron;

        public MailCount(int rest, int cron) {
            this.rest = rest;
            this.cron = cron;
        }

        public int getRest() {
            return rest;
        }

        public void setRest(int rest) {
            this.rest = rest;
        }

        public int getCron() {
            return cron;
        }

        public void setCron(int cron) {
            this.cron = cron;
        }
    }
}
