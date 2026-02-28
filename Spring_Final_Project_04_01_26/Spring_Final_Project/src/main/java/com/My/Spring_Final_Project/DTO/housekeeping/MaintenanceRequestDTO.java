package com.My.Spring_Final_Project.DTO.housekeeping;

import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.MaintenanceStatus;
import lombok.*;


@Data
@Getter
@Setter
public class MaintenanceRequestDTO {

    private Long id;

    private String description;

    private MaintenanceStatus status; // PENDING, DONE
}
