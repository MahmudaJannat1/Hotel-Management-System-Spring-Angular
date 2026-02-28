// src/app/services/models/housekeeping-stats.model.ts
export interface HousekeepingStatsDTO {
  totalTasks: number;
  pendingTasks: number;
  inProgressTasks: number;
  completedTasks: number;

  totalLaundryRequests: number;
  pendingLaundryRequests: number;
  doneLaundryRequests: number;

  totalMaintenanceRequests: number;
  pendingMaintenanceRequests: number;
  doneMaintenanceRequests: number;

  totalAssetMaintenanceLogs: number;
    totalStats: number; // ⚡ New

}
