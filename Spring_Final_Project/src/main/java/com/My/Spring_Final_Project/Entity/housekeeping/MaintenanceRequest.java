package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.MaintenanceStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "maintenance_requests")
public class MaintenanceRequest extends SoftDeleteEntity {
    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status; // PENDING, DONE
}
