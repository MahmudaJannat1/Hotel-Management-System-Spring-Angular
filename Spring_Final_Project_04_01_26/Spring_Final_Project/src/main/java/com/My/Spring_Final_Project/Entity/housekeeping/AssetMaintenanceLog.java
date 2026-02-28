package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "asset_maintenance_logs")
public class AssetMaintenanceLog extends SoftDeleteEntity {
    private String assetName;
    private String description;
    private LocalDateTime maintenanceDate;
}