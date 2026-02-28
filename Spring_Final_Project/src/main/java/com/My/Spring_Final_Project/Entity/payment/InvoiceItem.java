package com.My.Spring_Final_Project.Entity.payment;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
@Getter
@Setter
public class InvoiceItem extends SoftDeleteEntity {

    private String description;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;  // ✅ BigDecimal

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
