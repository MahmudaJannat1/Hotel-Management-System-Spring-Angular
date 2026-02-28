package com.My.Spring_Final_Project.Entity.customer;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loyalty_points_history")
@Getter
@Setter
public class LoyaltyPointsHistory extends SoftDeleteEntity {
    private Integer points;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
