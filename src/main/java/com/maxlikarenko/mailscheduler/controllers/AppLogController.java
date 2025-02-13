package com.maxlikarenko.mailscheduler.controllers;

import com.maxlikarenko.mailscheduler.models.AppLogResponseDTO;
import com.maxlikarenko.mailscheduler.services.AppLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class AppLogController {
    private final AppLogService appLogService;

    public AppLogController(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

    @GetMapping
    public ResponseEntity<List<AppLogResponseDTO>> getLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(appLogService.getLogs(page, size));
    }
}
