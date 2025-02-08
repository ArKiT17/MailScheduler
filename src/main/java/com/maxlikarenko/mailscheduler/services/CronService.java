package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import com.maxlikarenko.mailscheduler.entities.Cron;
import com.maxlikarenko.mailscheduler.models.*;
import com.maxlikarenko.mailscheduler.repositories.CronRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CronService {
    private final CronRepository cronRepository;

    public CronService(CronRepository cronRepository) {
        this.cronRepository = cronRepository;
    }

    public Cron findCronById(int id) {
        Cron cron = cronRepository.findById(id).orElse(null);
        if (cron == null)
            throw new AppException(HttpStatus.NOT_FOUND, "Cron не знайдено");
        return cron;
    }

    public CronDTO createCron(CronDTO newCron) {
        Cron cron = new Cron();
        cron.setExpression(newCron.getExpression());
        cron.setCreatedOn(LocalDateTime.now());
        return new CronDTO(cronRepository.save(cron));
    }

    public CronDTO updateCron(int id, CronDTO updatedCron) {
        Cron cron = findCronById(id);
        if (updatedCron.getExpression() != null)
            cron.setExpression(updatedCron.getExpression());
        return new CronDTO(cronRepository.save(cron));
    }

    public boolean deleteCron(int id) {
        Cron cron = findCronById(id);
        if (cron == null)
            throw new AppException(HttpStatus.NOT_FOUND, "Cron не знайдено");
        cronRepository.delete(cron);
        return true;
    }

    public List<Cron> getAllCron(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdOn").descending());
        return cronRepository.findAll(pageable).getContent();
    }
}
