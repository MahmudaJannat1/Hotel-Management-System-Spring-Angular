package com.My.Spring_Final_Project.DTO.inventory;

import lombok.Data;

@Data
public class InventoryStatsDTO {
    private Long totalAssets;
    private Long allocatedAssets;
    private Long availableAssets;

    private Long totalStockItems;
    private Long lowStockItems; // e.g., quantity < threshold
    private Long totalTransactions;
}
