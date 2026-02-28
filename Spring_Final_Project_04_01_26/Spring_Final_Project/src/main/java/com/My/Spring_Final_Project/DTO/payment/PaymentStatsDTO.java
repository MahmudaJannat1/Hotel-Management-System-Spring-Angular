package com.My.Spring_Final_Project.DTO.payment;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PaymentStatsDTO {
    private Long totalPayments;
    private Long completedPayments;
    private Long pendingPayments;
    private Long cancelledPayments;
    private BigDecimal totalRevenue; // sum of all completed payments
    private long totalInvoices;
    private long paidInvoices;
    private long pendingInvoices;
}
