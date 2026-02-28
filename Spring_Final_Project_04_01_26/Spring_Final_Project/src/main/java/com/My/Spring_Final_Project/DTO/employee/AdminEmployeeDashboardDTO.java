package com.My.Spring_Final_Project.DTO.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminEmployeeDashboardDTO {

    // ===== EMPLOYEE =====
    private long totalEmployees;
    private long activeEmployees;
    private long inactiveEmployees;

    // ===== ATTENDANCE =====
    private long todayPresent;
    private long todayAbsent;

    // ===== TASKS =====
    private long totalTasks;
    private long pendingTasks;
    private long inProgressTasks;
    private long completedTasks;

    // ===== PAYROLL =====
    private long totalPayrolls;
    private long paidPayrolls;
    private long pendingPayrolls;
    private double totalSalaryPaid;
}
