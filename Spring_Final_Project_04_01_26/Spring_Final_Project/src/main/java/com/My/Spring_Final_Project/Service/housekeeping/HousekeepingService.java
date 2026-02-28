package com.My.Spring_Final_Project.Service.housekeeping;

import com.My.Spring_Final_Project.DTO.housekeeping.*;

import java.util.List;

public interface HousekeepingService {

    // ---------- Housekeeping Task ----------
    HousekeepingTaskDTO createTask(HousekeepingTaskDTO dto);
    HousekeepingTaskDTO getTask(Long id);
    List<HousekeepingTaskDTO> getTasksByRoom(Long roomId);
    List<HousekeepingTaskDTO> getAllTasks();
    HousekeepingTaskDTO updateTaskStatus(Long id, String status);
    void deleteTask(Long id); // soft delete
    HousekeepingTaskDTO updateTask(Long id, HousekeepingTaskDTO dto);

    // ---------- Laundry ----------
    LaundryRequestDTO createLaundryRequest(LaundryRequestDTO dto);
    List<LaundryRequestDTO> getAllLaundryRequests();
    LaundryRequestDTO updateLaundryStatus(Long id, String status);
    void deleteLaundryRequest(Long id); // ✅ Delete

    // ---------- Maintenance ----------
    MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO dto);
    List<MaintenanceRequestDTO> getAllMaintenanceRequests();
    MaintenanceRequestDTO updateMaintenanceStatus(Long id, String status);
    void deleteMaintenanceRequest(Long id); // ✅ Delete

    // ---------- Asset Maintenance Log ----------
    AssetMaintenanceLogDTO createAssetMaintenanceLog(AssetMaintenanceLogDTO dto);
    List<AssetMaintenanceLogDTO> getAllAssetMaintenanceLogs();
    AssetMaintenanceLogDTO updateAssetLog(Long id, AssetMaintenanceLogDTO dto); // ✅ Update
    void deleteAssetLog(Long id); // ✅ Delete


    // ---------- Admin dashboard ----------
    HousekeepingStatsDTO getHousekeepingStats();
}
