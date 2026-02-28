package com.My.Spring_Final_Project.Entity.inventory;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "assets")
public class Asset extends SoftDeleteEntity {
    private String name;

    @ManyToOne
    private AssetCategory category;


}
