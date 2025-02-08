package com.maxlikarenko.mailscheduler.repositories;

import com.maxlikarenko.mailscheduler.entities.Cron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronRepository extends JpaRepository<Cron, Integer> {

}
