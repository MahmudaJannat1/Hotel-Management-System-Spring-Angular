package com.My.Spring_Final_Project.Entity.inventory;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "asset_categories")
public class AssetCategory extends SoftDeleteEntity {
    private String name;
    @Column(length = 500)
    private String description; // ✅ REQUIRED
}