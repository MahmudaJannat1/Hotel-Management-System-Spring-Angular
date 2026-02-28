package com.My.Spring_Final_Project.DTO.housekeeping;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class HousekeepingReportDTO {

    private Long id;

    private LocalDate reportDate;

    private Long totalTasks;

    private Long completedTasks;

    private Long pendingTasks;
}
