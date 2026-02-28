package com.My.Spring_Final_Project.DTO.housekeeping;

import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.LaundryStatus;
import lombok.Data;

@Data
public class LaundryRequestDTO {

    private Long id;

    private String description;

    private LaundryStatus status; // PENDING, DONE
}
