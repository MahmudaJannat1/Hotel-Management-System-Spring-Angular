package com.My.Spring_Final_Project.DTO.inventory;

import com.My.Spring_Final_Project.Enum.Inventory_Assets.TransactionType;
import lombok.Data;

@Data
public class StockTransactionDTO {
    private Long id;
    private Long stockItemId;
    private Integer quantity;
    private TransactionType type; // IN or OUT
}
