package com.My.Spring_Final_Project.Entity.corporate;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "corporate_invoices")
public class CorporateInvoice extends SoftDeleteEntity {
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private CorporateContract contract;

    private Double totalAmount;
    private LocalDateTime invoiceDate;
}