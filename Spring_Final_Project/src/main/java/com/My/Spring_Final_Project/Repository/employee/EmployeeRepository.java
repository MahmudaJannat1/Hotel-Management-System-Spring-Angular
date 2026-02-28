package com.My.Spring_Final_Project.Repository.employee;

import com.My.Spring_Final_Project.Entity.employee.Employee;
import com.My.Spring_Final_Project.Enum.employee.Department;
import com.My.Spring_Final_Project.Enum.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Active employees
    List<Employee> findByIsActiveTrue();

    // Optional: Role filter
    List<Employee> findByRole(Role role);

    // Optional: Department filter
    List<Employee> findByDepartment(Department department);
    long countByIsActiveTrue();
    long countByIsActiveFalse();

}
