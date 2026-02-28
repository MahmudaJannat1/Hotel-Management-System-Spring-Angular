package com.My.Spring_Final_Project.DTO.inventory;

import lombok.*;

@Data
@Getter
@Setter
public class AssetCategoryDTO {
    private Long id;
    private String name;
    private String description; // ✅ NEW

}
