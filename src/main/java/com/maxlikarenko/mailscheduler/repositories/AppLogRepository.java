package com.maxlikarenko.mailscheduler.repositories;

import com.maxlikarenko.mailscheduler.entities.AppLog;
import com.maxlikarenko.mailscheduler.models.AppLogResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppLogRepository extends JpaRepository<AppLog, Long> {
    @Query("""
        SELECT new com.maxlikarenko.mailscheduler.models.AppLogResponseDTO(
            u.username, u.email,
            COUNT(CASE WHEN l.type = 'REST' THEN 1 END),
            COUNT(CASE WHEN l.type = 'CRON' THEN 1 END),
            MIN(l.createdOn), MAX(l.createdOn)
            )
        FROM AppLog AS l
        JOIN AppUser AS u ON l.user = u
        GROUP BY u.id
        ORDER BY COUNT(l.id) DESC
    """)
    List<AppLogResponseDTO> getLogs(Pageable pageable);
}
