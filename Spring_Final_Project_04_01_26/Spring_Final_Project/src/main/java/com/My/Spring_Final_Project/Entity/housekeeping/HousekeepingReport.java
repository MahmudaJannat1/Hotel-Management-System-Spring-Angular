package com.My.Spring_Final_Project.Entity.housekeeping;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@Table(name = "housekeeping_reports")
public class HousekeepingReport extends SoftDeleteEntity {

    private LocalDate reportDate;

    private Long totalTasks;
    private Long completedTasks;
    private Long pendingTasks;
}
