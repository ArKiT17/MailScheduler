package com.maxlikarenko.mailscheduler.services;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import com.maxlikarenko.mailscheduler.models.AppException;
import com.maxlikarenko.mailscheduler.models.AppUserCreateDTO;
import com.maxlikarenko.mailscheduler.models.AppUserDTO;
import com.maxlikarenko.mailscheduler.repositories.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser findUserById(int id) {
        AppUser user = appUserRepository.findById(id).orElse(null);
        if (user == null)
            throw new AppException(HttpStatus.NOT_FOUND, "Користувача не знайдено");
        return user;
    }

    public AppUserDTO createUser(AppUserCreateDTO newUser) {
        AppUser appUser = new AppUser();
        appUser.setUsername(newUser.getUsername());
        appUser.setEmail(newUser.getEmail());
        appUser.setCreatedOn(LocalDateTime.now());
        return new AppUserDTO(appUserRepository.save(appUser));
    }

    public AppUserDTO updateUser(int id, AppUserDTO updatedUser) {
        AppUser user = findUserById(id);
        if (updatedUser.getUsername() != null)
            user.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null)
            user.setEmail(updatedUser.getEmail());
        return new AppUserDTO(appUserRepository.save(user));
    }

    public boolean deleteUser(int id) {
        AppUser user = findUserById(id);
        if (user == null)
            throw new AppException(HttpStatus.NOT_FOUND, "Користувача не знайдено");
        appUserRepository.delete(user);
        return true;
    }

    public List<AppUser> getAllUsers(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdOn").descending());
        Page<AppUser> userPage;
        if (filter != null && !filter.isEmpty()) {
            userPage = appUserRepository.findByUsernameContainsOrEmailContaining(filter, filter, pageable);
        } else {
            userPage = appUserRepository.findAll(pageable);
        }
        return userPage.getContent();
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}
