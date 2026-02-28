package com.My.Spring_Final_Project.DTO.employee;

import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.TaskTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeTaskDTO {
    private Long id;
    private Long employeeId;
    private TaskTypeEnum taskType;
    private Long taskId;
    private String description;
    private TaskStatusEnum status;
    private LocalDate assignedDate;
    private LocalDate completedDate;
}
