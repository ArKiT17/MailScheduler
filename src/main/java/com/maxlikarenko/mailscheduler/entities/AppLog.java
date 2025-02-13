package com.maxlikarenko.mailscheduler.entities;

import com.maxlikarenko.mailscheduler.LogType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class AppLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType type;
    @Column(nullable = false)
    private LocalDateTime createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
