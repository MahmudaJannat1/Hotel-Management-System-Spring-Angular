package com.My.Spring_Final_Project.DTO.inventory;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssetAllocationDTO {
    private Long id;
    private Long assetId;
    private String allocatedTo;
    private LocalDateTime allocatedAt;
}
