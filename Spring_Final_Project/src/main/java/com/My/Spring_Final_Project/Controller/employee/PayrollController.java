package com.My.Spring_Final_Project.Controller.employee;

import com.My.Spring_Final_Project.DTO.employee.PayrollDTO;
import com.My.Spring_Final_Project.Entity.employee.Payroll;
import com.My.Spring_Final_Project.Service.employee.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    // ===== Create Payroll =====
    @PostMapping
    public ResponseEntity<PayrollDTO> createPayroll(@RequestBody PayrollDTO dto) {
        return ResponseEntity.ok(payrollService.createPayroll(dto));
    }

    // ===== Get Payroll by ID =====
    @GetMapping("/{id}")
    public ResponseEntity<PayrollDTO> getPayroll(@PathVariable Long id) {
        return ResponseEntity.ok(payrollService.getPayroll(id));
    }

    // ===== Get All Payrolls =====
    @GetMapping
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls() {
        return ResponseEntity.ok(payrollService.getAllPayrolls());
    }

    // ===== Update Payroll (FULL UPDATE) =====
    @PutMapping("/{id}")
    public ResponseEntity<PayrollDTO> updatePayroll(
            @PathVariable Long id,
            @RequestBody PayrollDTO dto
    ) {
        return ResponseEntity.ok(payrollService.updatePayroll(id, dto));
    }


    // ===== Get Payrolls by Employee =====
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollDTO>> getPayrollsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollsByEmployee(employeeId));
    }

    // ===== Update Payroll Status =====
    @PutMapping("/{id}/status")
    public ResponseEntity<PayrollDTO> updatePayrollStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(payrollService.updatePayrollStatus(id, status));
    }

    // ===== Delete Payroll =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Payslip PDF download
    @GetMapping("/{id}/payslip")
    public ResponseEntity<byte[]> downloadPayslip(@PathVariable Long id) {
        Payroll payroll = payrollService.getPayrollEntity(id); // add this helper in service
        byte[] pdfBytes = payrollService.generatePayslipPdf(payroll);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payslip.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


}
