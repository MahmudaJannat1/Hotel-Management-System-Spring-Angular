package com.My.Spring_Final_Project.DTO.housekeeping;

import lombok.*;

@Data
@Getter
@Setter
public class HousekeepingStatsDTO {
    private Long totalTasks;
    private Long pendingTasks;
    private Long inProgressTasks;
    private Long completedTasks;

    private Long totalLaundryRequests;
    private Long pendingLaundryRequests;
    private Long doneLaundryRequests;

    private Long totalMaintenanceRequests;
    private Long pendingMaintenanceRequests;
    private Long doneMaintenanceRequests;

    private Long totalAssetMaintenanceLogs;

    private Long totalStats; // ⚡ Change Double → Long for integer sum


}
