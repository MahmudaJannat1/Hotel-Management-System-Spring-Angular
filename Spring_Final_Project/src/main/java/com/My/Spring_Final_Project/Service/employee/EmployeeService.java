package com.My.Spring_Final_Project.Service.employee;


import com.My.Spring_Final_Project.DTO.employee.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO dto);

    EmployeeDTO getEmployee(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    void deleteEmployee(Long id); // soft delete
}
