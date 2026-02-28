package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.EmployeeDTO;
import com.My.Spring_Final_Project.Entity.employee.Employee;
import com.My.Spring_Final_Project.Repository.employee.EmployeeRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setPhone(dto.getPhone());
        emp.setRole(dto.getRole());
        emp.setDepartment(dto.getDepartment());
        emp.setJoiningDate(dto.getJoiningDate());
        emp.setSalary(dto.getSalary());

        employeeRepository.save(emp);
        dto.setId(emp.getId());
        return dto;
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapToDTO(emp);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findByIsActiveTrue()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if(dto.getName() != null) emp.setName(dto.getName());
        if(dto.getEmail() != null) emp.setEmail(dto.getEmail());
        if(dto.getPhone() != null) emp.setPhone(dto.getPhone());
        if(dto.getRole() != null) emp.setRole(dto.getRole());
        if(dto.getDepartment() != null) emp.setDepartment(dto.getDepartment());
        if(dto.getJoiningDate() != null) emp.setJoiningDate(dto.getJoiningDate());
        if(dto.getSalary() != null) emp.setSalary(dto.getSalary());

        employeeRepository.save(emp);
        return mapToDTO(emp);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setIsActive(false); // soft delete
        emp.setDeletedAt(LocalDateTime.now());
    }

    private EmployeeDTO mapToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setPhone(emp.getPhone());
        dto.setRole(emp.getRole());
        dto.setDepartment(emp.getDepartment());
        dto.setJoiningDate(emp.getJoiningDate());
        dto.setSalary(emp.getSalary());
        return dto;
    }
}
