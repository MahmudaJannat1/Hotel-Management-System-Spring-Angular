package com.My.Spring_Final_Project.Entity.payment;

import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Event.PaymentMethod;
import com.My.Spring_Final_Project.Enum.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment extends SoftDeleteEntity {

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;  // BigDecimal for monetary precision

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;  // Enum for type safety

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;  // Lazy load BookingPaymentStatus

    @Column(length = 100, unique = true)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;   // ⚠ field নাম users

    @Column(length = 500)
    private String notes;

    @OneToOne
    @JoinColumn(name = "invoice_id", unique = true)
    private Invoice invoice;
}
