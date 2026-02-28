package com.My.Spring_Final_Project.Entity.inventory;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "stock_items")
public class StockItem extends SoftDeleteEntity {
    private String name;
    private Integer quantity;
    private Integer minQuantity; // low stock alert

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AssetCategory category;
}
