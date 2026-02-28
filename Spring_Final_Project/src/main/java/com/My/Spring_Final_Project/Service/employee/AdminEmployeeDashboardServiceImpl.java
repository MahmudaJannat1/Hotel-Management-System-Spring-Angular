package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.AdminEmployeeDashboardDTO;
import com.My.Spring_Final_Project.Enum.employee.PaymentStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.PayrollStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.TaskStatusEnum;
import com.My.Spring_Final_Project.Repository.employee.AttendanceRepository;
import com.My.Spring_Final_Project.Repository.employee.EmployeeRepository;
import com.My.Spring_Final_Project.Repository.employee.EmployeeTaskRepository;
import com.My.Spring_Final_Project.Repository.employee.PayrollRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

    @Service
    @RequiredArgsConstructor
    @Transactional(readOnly = true)
    public class AdminEmployeeDashboardServiceImpl implements AdminEmployeeDashboardService {

        private final EmployeeRepository employeeRepository;
        private final AttendanceRepository attendanceRepository;
        private final EmployeeTaskRepository taskRepository;
        private final PayrollRepository payrollRepository;

        @Override
        public AdminEmployeeDashboardDTO getDashboardStats() {

            LocalDate today = LocalDate.now();

            AdminEmployeeDashboardDTO dto = new AdminEmployeeDashboardDTO();

            // ===== EMPLOYEE =====
            dto.setTotalEmployees(employeeRepository.count());
            dto.setActiveEmployees(employeeRepository.countByIsActiveTrue());
            dto.setInactiveEmployees(employeeRepository.countByIsActiveFalse());

            // ===== ATTENDANCE =====
            dto.setTodayPresent(attendanceRepository.countByDateAndPresent(today, true));
            dto.setTodayAbsent(attendanceRepository.countByDateAndPresent(today, false));

            // ===== TASKS =====
            dto.setTotalTasks(taskRepository.count());
            dto.setPendingTasks(taskRepository.countByStatus(TaskStatusEnum.PENDING));
            dto.setInProgressTasks(taskRepository.countByStatus(TaskStatusEnum.IN_PROGRESS));
            dto.setCompletedTasks(taskRepository.countByStatus(TaskStatusEnum.DONE));

            // ===== PAYROLL =====
            dto.setTotalPayrolls(payrollRepository.count());
            dto.setPaidPayrolls(payrollRepository.countByStatus(PaymentStatusEnum.PAID));
            dto.setPendingPayrolls(payrollRepository.countByStatus(PaymentStatusEnum.PENDING));

            BigDecimal totalPaid = payrollRepository.sumSalaryByStatus(PaymentStatusEnum.PAID);
            dto.setTotalSalaryPaid(totalPaid != null ? totalPaid.doubleValue() : 0.0);

            return dto;
        }
    }


