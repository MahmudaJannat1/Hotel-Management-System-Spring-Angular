package com.My.Spring_Final_Project.Entity.inventory;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Inventory_Assets.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "stock_transactions")
public class StockTransaction extends SoftDeleteEntity {
    @ManyToOne
    private StockItem stockItem;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // IN, OUT
}
