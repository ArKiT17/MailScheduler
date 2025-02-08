package com.maxlikarenko.mailscheduler.controllers;

import com.maxlikarenko.mailscheduler.entities.Cron;
import com.maxlikarenko.mailscheduler.models.CronDTO;
import com.maxlikarenko.mailscheduler.services.CronService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cron")
public class CronController {
    private final CronService cronService;

    public CronController(CronService cronService) {
        this.cronService = cronService;
    }

    @PostMapping
    public ResponseEntity<CronDTO> addCron(@RequestBody @Valid CronDTO newCron) {
        return ResponseEntity.ok(cronService.createCron(newCron));
    }

    @PutMapping("/{cronId}")
    public ResponseEntity<CronDTO> updateCron(@PathVariable int cronId, @RequestBody @Valid CronDTO updatedCron) {
        return ResponseEntity.ok(cronService.updateCron(cronId, updatedCron));
    }

    @DeleteMapping("/{cronId}")
    public ResponseEntity<Map<String, Boolean>> deleteCron(@PathVariable int cronId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", cronService.deleteCron(cronId));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Cron>> getAllCron(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(cronService.getAllCron(page, size));
    }
}
