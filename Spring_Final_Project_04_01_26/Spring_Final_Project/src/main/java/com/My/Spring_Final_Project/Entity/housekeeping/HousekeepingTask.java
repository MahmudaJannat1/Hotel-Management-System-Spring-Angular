package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "housekeeping_tasks")
public class HousekeepingTask extends SoftDeleteEntity {

    @ManyToOne(optional = false)
    private Room room;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; // PENDING, IN_PROGRESS, COMPLETED

    private String assignedTo; // staff name / staff code
}
