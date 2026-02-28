package com.My.Spring_Final_Project.Repository.employee;

import com.My.Spring_Final_Project.Entity.employee.EmployeeTask;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    List<EmployeeTask> findByEmployeeId(Long employeeId);
    List<EmployeeTask> findByStatus(String status);

    long countByStatus(TaskStatusEnum status);

}
