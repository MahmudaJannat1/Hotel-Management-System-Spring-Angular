package com.My.Spring_Final_Project.Entity.customer;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_memberships")
@Getter
@Setter
public class CustomerMembership extends SoftDeleteEntity {
    private String membershipType;
    private LocalDateTime validUntil;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
