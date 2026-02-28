package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.PayrollDTO;
import com.My.Spring_Final_Project.Entity.employee.Employee;
import com.My.Spring_Final_Project.Entity.employee.Payroll;
import com.My.Spring_Final_Project.Enum.employee.PaymentStatusEnum;
import com.My.Spring_Final_Project.Repository.employee.EmployeeRepository;
import com.My.Spring_Final_Project.Repository.employee.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public PayrollDTO createPayroll(PayrollDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Payroll payroll = new Payroll();
        payroll.setEmployee(emp);
        payroll.setSalaryType(dto.getSalaryType());
        payroll.setAmount(
                dto.getAmount() == null ? BigDecimal.ZERO : dto.getAmount()
        );
        payroll.setPaymentDate(dto.getPaymentDate());
        payroll.setStatus(
                dto.getStatus() == null ? PaymentStatusEnum.PENDING : dto.getStatus()
        );

        payrollRepository.save(payroll);

        dto.setId(payroll.getId());
        return dto;
    }


    @Override
    public PayrollDTO updatePayroll(Long id, PayrollDTO dto) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        payroll.setEmployee(emp);
        payroll.setSalaryType(dto.getSalaryType());
        payroll.setAmount(dto.getAmount());
        payroll.setPaymentDate(dto.getPaymentDate());
        payroll.setStatus(dto.getStatus());

        payrollRepository.save(payroll);

        return mapToDTO(payroll);
    }

    @Override
    public PayrollDTO getPayroll(Long id) {
        return mapToDTO(payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found")));
    }

    @Override
    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PayrollDTO> getPayrollsByEmployee(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }


//    @Override
//    public BigDecimal getTotalPaidSalary() {
//        return payrollRepository.sumSalaryByStatus(PaymentStatusEnum.PAID);
//    }

    @Override
    public PayrollDTO updatePayrollStatus(Long id, String status) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));
        payroll.setStatus(PaymentStatusEnum.valueOf(status));
        payrollRepository.save(payroll);
        return mapToDTO(payroll);
    }

    @Override
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }

    @Override
    public byte[] generatePayslipPdf(Payroll payroll) {

        Employee employee = payroll.getEmployee(); // assuming payroll has Employee

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Hotel Name").setBold());
            document.add(new Paragraph("Employee: " + employee.getName()));
            document.add(new Paragraph("Department: " + employee.getDepartment()));
            LocalDate date = payroll.getPaymentDate() != null
                    ? payroll.getPaymentDate()
                    : LocalDate.now();

            document.add(new Paragraph(
                    "Month: " + date.getMonth() + " " + date.getYear()
            ));
            document.add(new Paragraph("Amount: " + payroll.getAmount()));
            document.add(new Paragraph("Bonus: " + payroll.getAmount()));
            document.add(new Paragraph("Status: " + payroll.getStatus()));
            document.add(new Paragraph("Generated at: " + LocalDate.now()));

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }

        return baos.toByteArray();
    }
    private PayrollDTO mapToDTO(Payroll payroll) {
        PayrollDTO dto = new PayrollDTO();
        dto.setId(payroll.getId());
        dto.setEmployeeId(payroll.getEmployee().getId());
        dto.setSalaryType(payroll.getSalaryType());
        dto.setAmount(payroll.getAmount());
        dto.setPaymentDate(payroll.getPaymentDate());
        dto.setStatus(payroll.getStatus());
        return dto;
    }

    @Override
    public Payroll getPayrollEntity(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));
    }

}
