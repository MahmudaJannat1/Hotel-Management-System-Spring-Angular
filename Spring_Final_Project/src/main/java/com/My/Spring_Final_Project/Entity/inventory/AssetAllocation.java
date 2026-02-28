package com.My.Spring_Final_Project.Entity.inventory;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "asset_allocation")
public class AssetAllocation extends SoftDeleteEntity {
    @ManyToOne
    private Asset asset;

    private String allocatedTo;
    private LocalDateTime allocatedAt;
}
