package com.maxlikarenko.mailscheduler.repositories;

import com.maxlikarenko.mailscheduler.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Page<AppUser> findByUsernameContainsOrEmailContaining(String username, String email, Pageable pageable);
}
