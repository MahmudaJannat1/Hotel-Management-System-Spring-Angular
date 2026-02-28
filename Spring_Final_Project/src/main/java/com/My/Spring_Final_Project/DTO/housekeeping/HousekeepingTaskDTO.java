package com.My.Spring_Final_Project.DTO.housekeeping;

import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.TaskStatus;
import lombok.*;

@Data
@Getter
@Setter
public class HousekeepingTaskDTO {

    private Long id;

    private Long roomId;

    private String description;

    private TaskStatus status; // PENDING, IN_PROGRESS, COMPLETED

    private String assignedTo; // staff name / code
}
