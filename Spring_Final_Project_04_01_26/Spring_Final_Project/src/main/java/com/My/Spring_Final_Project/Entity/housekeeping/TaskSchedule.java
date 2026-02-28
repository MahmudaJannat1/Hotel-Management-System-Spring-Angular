package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.housekeeping.HousekeepingTask;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "housekeeping_task_schedule")
public class TaskSchedule extends SoftDeleteEntity {

    @OneToOne
    private HousekeepingTask task;

    private LocalDateTime scheduledAt;
    private LocalDateTime completedAt;
}
