package com.My.Spring_Final_Project.Controller.employee;

import com.My.Spring_Final_Project.DTO.employee.EmployeeTaskDTO;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import com.My.Spring_Final_Project.Service.employee.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-tasks")
@RequiredArgsConstructor
public class EmployeeTaskController {

    private final EmployeeTaskService taskService;

    @PostMapping
    public ResponseEntity<EmployeeTaskDTO> assignTask(@RequestBody EmployeeTaskDTO dto) {
        return ResponseEntity.ok(taskService.assignTask(dto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeTaskDTO> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam TaskStatusEnum status
    ) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeTaskDTO>> getTasksByEmployee(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(taskService.getTasksByEmployee(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeTaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
