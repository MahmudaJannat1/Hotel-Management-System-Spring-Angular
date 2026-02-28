package com.My.Spring_Final_Project.DTO.housekeeping;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class AssetMaintenanceLogDTO {

    private Long id;

    private String assetName;

    private String description;

    private LocalDateTime maintenanceDate;
}
