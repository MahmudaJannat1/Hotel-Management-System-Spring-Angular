package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.EmployeeTaskDTO;
import com.My.Spring_Final_Project.Entity.employee.Employee;
import com.My.Spring_Final_Project.Entity.employee.EmployeeTask;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import com.My.Spring_Final_Project.Repository.employee.EmployeeRepository;
import com.My.Spring_Final_Project.Repository.employee.EmployeeTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

    private final EmployeeTaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeTaskDTO assignTask(EmployeeTaskDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeTask task = new EmployeeTask();
        task.setEmployee(emp);
        task.setTaskType(dto.getTaskType());
        task.setTaskId(dto.getTaskId());
        task.setDescription(dto.getDescription());
        task.setStatus(
                dto.getStatus() != null ? dto.getStatus() : TaskStatusEnum.PENDING
        );
        task.setAssignedDate(LocalDate.now());

        taskRepository.save(task);

        dto.setId(task.getId());
        dto.setStatus(task.getStatus());
        dto.setAssignedDate(task.getAssignedDate());
        return dto;
    }

    @Override
    public EmployeeTaskDTO updateTaskStatus(Long id,  TaskStatusEnum status) {
        EmployeeTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        if (status == TaskStatusEnum.DONE) {
            task.setCompletedDate(LocalDate.now());
        }

        taskRepository.save(task);
        return mapToDTO(task);
    }

    @Override
    public List<EmployeeTaskDTO> getTasksByEmployee(Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeTaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        EmployeeTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }


    private EmployeeTaskDTO mapToDTO(EmployeeTask task) {
        EmployeeTaskDTO dto = new EmployeeTaskDTO();
        dto.setId(task.getId());
        dto.setEmployeeId(task.getEmployee().getId());
        dto.setTaskType(task.getTaskType());
        dto.setTaskId(task.getTaskId());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setAssignedDate(task.getAssignedDate());
        dto.setCompletedDate(task.getCompletedDate());
        return dto;
    }
}
