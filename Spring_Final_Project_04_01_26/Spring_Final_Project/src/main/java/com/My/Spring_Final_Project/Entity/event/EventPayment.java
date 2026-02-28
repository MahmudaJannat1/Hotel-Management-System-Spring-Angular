package com.My.Spring_Final_Project.Entity.event;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Event.PaymentMethod;
import jakarta.persistence.*;



@Entity
@Table(name = "event_payments")
public class EventPayment extends SoftDeleteEntity {
    @ManyToOne
    private EventBooking booking;

    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // CASH, CREDIT, Bkash, etc.
}