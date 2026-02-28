package com.My.Spring_Final_Project.DTO.inventory;

import lombok.Data;

@Data
public class StockItemDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private Integer minQuantity;
    private Long categoryId;          // relation to AssetCategory
    private String categoryName;
}
