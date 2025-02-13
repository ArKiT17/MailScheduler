package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.LogType;
import com.maxlikarenko.mailscheduler.entities.AppLog;
import com.maxlikarenko.mailscheduler.entities.AppUser;
import com.maxlikarenko.mailscheduler.models.AppLogResponseDTO;
import com.maxlikarenko.mailscheduler.repositories.AppLogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppLogService {
    private final AppLogRepository appLogRepository;

    public AppLogService(AppLogRepository appLogRepository) {
        this.appLogRepository = appLogRepository;
    }

    public void addLog(AppUser user, LogType type) {
        AppLog appLog = new AppLog();
        appLog.setUser(user);
        appLog.setType(type);
        appLog.setCreatedOn(LocalDateTime.now());
        appLogRepository.save(appLog);
    }

    public List<AppLogResponseDTO> getLogs(int page, int size) {
        return appLogRepository.getLogs(PageRequest.of(page, size));
    }
}
