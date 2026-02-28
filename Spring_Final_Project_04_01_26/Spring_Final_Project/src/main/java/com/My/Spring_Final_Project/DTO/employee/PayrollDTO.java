package com.My.Spring_Final_Project.DTO.employee;

import com.My.Spring_Final_Project.Enum.employee.PaymentStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.SalaryTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PayrollDTO {

    private Long id;
    private Long employeeId;
    private SalaryTypeEnum salaryType;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private PaymentStatusEnum status;
}
