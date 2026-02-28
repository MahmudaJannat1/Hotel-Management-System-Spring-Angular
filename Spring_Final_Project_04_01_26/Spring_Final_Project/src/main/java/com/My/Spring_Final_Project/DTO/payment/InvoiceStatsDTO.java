package com.My.Spring_Final_Project.DTO.payment;

import lombok.Data;

@Data
public class InvoiceStatsDTO {
    private Long totalInvoices;
    private Long pendingInvoices;
    private Long paidInvoices;
    private Long cancelledInvoices;
}
