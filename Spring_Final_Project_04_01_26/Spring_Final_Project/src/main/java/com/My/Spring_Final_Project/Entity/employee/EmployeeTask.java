package com.My.Spring_Final_Project.Entity.employee;

import com.My.Spring_Final_Project.Entity.common.BaseEntity;
import com.My.Spring_Final_Project.Entity.housekeeping.HousekeepingTask;
import com.My.Spring_Final_Project.Entity.housekeeping.MaintenanceRequest;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.TaskTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EmployeeTask extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    private Long taskId; // id of HousekeepingTask or MaintenanceRequest

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskTypeEnum taskType;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;
    private LocalDate assignedDate;

    private LocalDate completedDate;
}
