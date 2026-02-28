package com.My.Spring_Final_Project.Entity.payment;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
public class PaymentMethod extends SoftDeleteEntity {

    private String methodName; // e.g., "Cash", "Credit Card", "Bkash"

    private String description;
}
