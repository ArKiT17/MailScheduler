package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.entities.Cron;
import com.maxlikarenko.mailscheduler.models.AppException;
import com.maxlikarenko.mailscheduler.models.CronDTO;
import com.maxlikarenko.mailscheduler.repositories.CronRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CronService {
    private final CronRepository cronRepository;
    private final SchedulerService schedulerService;

    public CronService(CronRepository cronRepository, SchedulerService schedulerService) {
        this.cronRepository = cronRepository;
        this.schedulerService = schedulerService;
    }

    @PostConstruct
    public void loadCronsOnStartup() {
        List<Cron> crons = cronRepository.findAll();
        for (Cron cron : crons)
            schedulerService.scheduleSendInfo(cron);
    }

    public Cron findCronById(int id) {
        Cron cron = cronRepository.findById(id).orElse(null);
        if (cron == null)
            throw new AppException(HttpStatus.NOT_FOUND, "Cron не знайдено");
        return cron;
    }

    public CronDTO createCron(CronDTO newCron) {
        if (isInvalidExpression(newCron.getExpression())) {
            throw new AppException("Невірний формат cron-виразу");
        }
        Cron cron = new Cron();
        cron.setExpression(newCron.getExpression());
        cron.setCreatedOn(LocalDateTime.now());
        cron = cronRepository.save(cron);
        schedulerService.scheduleSendInfo(cron);
        return new CronDTO(cron);
    }

    public CronDTO updateCron(int id, CronDTO updatedCron) {
        if (isInvalidExpression(updatedCron.getExpression())) {
            throw new AppException("Невірний формат cron-виразу");
        }
        Cron cron = findCronById(id);
        cron.setExpression(updatedCron.getExpression());
        schedulerService.updateSchedule(cron);
        return new CronDTO(cronRepository.save(cron));
    }

    public boolean deleteCron(int id) {
        Cron cron = findCronById(id);
        schedulerService.cancelSchedule(id);
        cronRepository.delete(cron);
        return true;
    }

    public List<Cron> getAllCrons(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdOn").descending());
        return cronRepository.findAll(pageable).getContent();
    }

    private boolean isInvalidExpression(String expression) {
        try {
            new CronTrigger(expression);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
