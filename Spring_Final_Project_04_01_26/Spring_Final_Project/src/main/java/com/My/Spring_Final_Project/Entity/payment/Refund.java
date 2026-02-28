package com.My.Spring_Final_Project.Entity.payment;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@Setter
public class Refund extends SoftDeleteEntity {

    private Double amount;
    private LocalDateTime refundDate;
    private String reason;
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
