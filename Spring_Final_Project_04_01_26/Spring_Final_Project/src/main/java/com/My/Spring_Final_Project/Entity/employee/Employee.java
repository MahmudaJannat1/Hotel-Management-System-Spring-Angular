package com.My.Spring_Final_Project.Entity.employee;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.employee.Role;
import com.My.Spring_Final_Project.Enum.employee.Department;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Employee extends SoftDeleteEntity {

    private String name;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;          // Role enum

    @Enumerated(EnumType.STRING)
    private Department department; // Department enum

    private LocalDate joiningDate;
    private Double salary;
}
