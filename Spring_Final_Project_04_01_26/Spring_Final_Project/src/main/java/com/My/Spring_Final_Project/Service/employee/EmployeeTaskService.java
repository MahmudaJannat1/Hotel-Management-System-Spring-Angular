package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.EmployeeTaskDTO;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;

import java.util.List;

public interface EmployeeTaskService {
    EmployeeTaskDTO assignTask(EmployeeTaskDTO dto);
    EmployeeTaskDTO updateTaskStatus(Long id,  TaskStatusEnum status);
    List<EmployeeTaskDTO> getTasksByEmployee(Long employeeId);
    List<EmployeeTaskDTO> getAllTasks();
    void deleteTask(Long id);   // ✅ ADD THIS

}
