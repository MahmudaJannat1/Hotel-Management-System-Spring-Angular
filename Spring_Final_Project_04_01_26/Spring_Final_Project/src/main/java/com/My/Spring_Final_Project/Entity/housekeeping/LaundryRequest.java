package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.LaundryStatus;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "laundry_requests")
public class LaundryRequest extends SoftDeleteEntity {
    private String description;

    @Enumerated(EnumType.STRING)
    private LaundryStatus status; // PENDING, DONE
}
