package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.TaskSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskScheduleRepository extends JpaRepository<TaskSchedule, Long> {

    List<TaskSchedule> findByScheduledAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<TaskSchedule> findByIsActiveTrue();
}
