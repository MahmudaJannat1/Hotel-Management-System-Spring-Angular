package com.My.Spring_Final_Project.Repository.employee;

import com.My.Spring_Final_Project.Entity.employee.Payroll;
import com.My.Spring_Final_Project.Enum.employee.PaymentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Get payrolls of a specific employee
    List<Payroll> findByEmployeeId(Long employeeId);

    // Count payrolls by payment status
    long countByStatus(PaymentStatusEnum status);

    // Sum of paid salary amount
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payroll p WHERE p.status = :status")
    BigDecimal sumSalaryByStatus(PaymentStatusEnum status);
}
