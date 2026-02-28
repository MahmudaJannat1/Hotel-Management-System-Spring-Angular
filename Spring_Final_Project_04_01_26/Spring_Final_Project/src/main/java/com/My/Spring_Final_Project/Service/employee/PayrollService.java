package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.PayrollDTO;
import com.My.Spring_Final_Project.Entity.employee.Payroll;

import java.util.List;

public interface PayrollService {
    PayrollDTO createPayroll(PayrollDTO dto);
    PayrollDTO getPayroll(Long id);
    List<PayrollDTO> getAllPayrolls();
    List<PayrollDTO> getPayrollsByEmployee(Long employeeId);
    PayrollDTO updatePayrollStatus(Long id, String status);
    void deletePayroll(Long id);
    byte[] generatePayslipPdf(Payroll payroll);
    Payroll getPayrollEntity(Long id); // <-- new
    PayrollDTO updatePayroll(Long id, PayrollDTO dto);



}
