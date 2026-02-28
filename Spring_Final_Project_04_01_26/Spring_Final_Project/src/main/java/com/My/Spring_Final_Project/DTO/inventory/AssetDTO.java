package com.My.Spring_Final_Project.DTO.inventory;

import lombok.*;

@Getter
@Setter
@Data
public class AssetDTO {
    private Long id;
    private String name;
    private Long categoryId; // reference to AssetCategory
}
