package com.maxlikarenko.mailscheduler.controllers;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import com.maxlikarenko.mailscheduler.models.AppUserCreateDTO;
import com.maxlikarenko.mailscheduler.models.AppUserDTO;
import com.maxlikarenko.mailscheduler.services.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> registerUser(@RequestBody @Valid AppUserCreateDTO newUser) {
        return ResponseEntity.ok(appUserService.createUser(newUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable int userId, @RequestBody @Valid AppUserDTO updatedUser) {
        return ResponseEntity.ok(appUserService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable int userId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", appUserService.deleteUser(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filter) {
        return ResponseEntity.ok(appUserService.getAllUsers(page, size, filter));
    }
}
