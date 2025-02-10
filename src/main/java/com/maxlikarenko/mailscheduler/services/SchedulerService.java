package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.entities.Cron;
import com.maxlikarenko.mailscheduler.models.AppException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {
    private final TaskScheduler scheduler;
    private final MailService mailService;
    private final Map<Integer, ScheduledFuture<?>> jobs = new ConcurrentHashMap<>();

    public SchedulerService(TaskScheduler scheduler, MailService mailService) {
        this.scheduler = scheduler;
        this.mailService = mailService;
    }

    public void scheduleSendInfo(Cron cron) {
        CronTrigger cronTrigger;
        try {
            cronTrigger = new CronTrigger(cron.getExpression());
        } catch (Exception e) {
            throw new AppException("Зчитано некоректний cron-вираз");
        }
        ScheduledFuture<?> schedule = scheduler.schedule(mailService::sendInfoToAllUsers, cronTrigger);
        jobs.put(cron.getId(), schedule);
    }

    public void cancelSchedule(int cronId) {
        ScheduledFuture<?> schedule = jobs.remove(cronId);
        if (schedule != null)
            schedule.cancel(false);
    }

    public void updateSchedule(Cron cron) {
        cancelSchedule(cron.getId());
        scheduleSendInfo(cron);
    }
}
