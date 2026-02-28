package com.My.Spring_Final_Project.DTO.employee;

import com.My.Spring_Final_Project.Enum.employee.Role;
import com.My.Spring_Final_Project.Enum.employee.Department;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    private Role role;
    private Department department;

    private LocalDate joiningDate;
    private Double salary;
}
