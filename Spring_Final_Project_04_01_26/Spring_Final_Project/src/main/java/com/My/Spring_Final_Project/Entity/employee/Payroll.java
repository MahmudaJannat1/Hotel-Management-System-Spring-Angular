package com.My.Spring_Final_Project.Entity.employee;

import com.My.Spring_Final_Project.Entity.common.BaseEntity;
import com.My.Spring_Final_Project.Enum.employee.PaymentStatusEnum;
import com.My.Spring_Final_Project.Enum.employee.SalaryTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Payroll extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private SalaryTypeEnum salaryType;

    private BigDecimal amount;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;


}
