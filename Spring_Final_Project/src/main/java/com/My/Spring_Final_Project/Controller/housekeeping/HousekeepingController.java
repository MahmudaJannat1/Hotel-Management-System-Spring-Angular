package com.My.Spring_Final_Project.Controller.housekeeping;

import com.My.Spring_Final_Project.DTO.housekeeping.HousekeepingTaskDTO;
import com.My.Spring_Final_Project.DTO.housekeeping.LaundryRequestDTO;
import com.My.Spring_Final_Project.DTO.housekeeping.MaintenanceRequestDTO;
import com.My.Spring_Final_Project.DTO.housekeeping.AssetMaintenanceLogDTO;
import com.My.Spring_Final_Project.DTO.housekeeping.HousekeepingStatsDTO;
import com.My.Spring_Final_Project.Service.housekeeping.HousekeepingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeping")
@RequiredArgsConstructor
public class HousekeepingController {

    private final HousekeepingService housekeepingService;

    // ===== Housekeeping Tasks =====
    @PostMapping("/tasks")
    public ResponseEntity<HousekeepingTaskDTO> createTask(@RequestBody HousekeepingTaskDTO dto) {
        return ResponseEntity.ok(housekeepingService.createTask(dto));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<HousekeepingTaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(housekeepingService.getTask(id));
    }

    @GetMapping("/tasks/room/{roomId}")
    public ResponseEntity<List<HousekeepingTaskDTO>> getTasksByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(housekeepingService.getTasksByRoom(roomId));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<HousekeepingTaskDTO>> getAllTasks() {
        return ResponseEntity.ok(housekeepingService.getAllTasks());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<HousekeepingTaskDTO> updateTask(@PathVariable Long id, @RequestBody HousekeepingTaskDTO dto) {
        return ResponseEntity.ok(housekeepingService.updateTask(id, dto));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        housekeepingService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // ===== Laundry Requests =====
    @PostMapping("/laundry")
    public ResponseEntity<LaundryRequestDTO> createLaundry(@RequestBody LaundryRequestDTO dto) {
        return ResponseEntity.ok(housekeepingService.createLaundryRequest(dto));
    }

    @GetMapping("/laundry")
    public ResponseEntity<List<LaundryRequestDTO>> getAllLaundry() {
        return ResponseEntity.ok(housekeepingService.getAllLaundryRequests());
    }

    @PutMapping("/laundry/{id}")
    public ResponseEntity<LaundryRequestDTO> updateLaundry(@PathVariable Long id,
                                                           @RequestBody LaundryRequestDTO dto) {
        // For simplicity, you can update status only or full DTO
        if(dto.getStatus() != null){
            return ResponseEntity.ok(housekeepingService.updateLaundryStatus(id, dto.getStatus().name()));
        }
        return ResponseEntity.ok(housekeepingService.createLaundryRequest(dto)); // fallback
    }

    @DeleteMapping("/laundry/{id}")
    public ResponseEntity<Void> deleteLaundry(@PathVariable Long id) {
        housekeepingService.deleteLaundryRequest(id);
        return ResponseEntity.noContent().build();
    }

    // ===== Maintenance Requests =====
    @PostMapping("/maintenance")
    public ResponseEntity<MaintenanceRequestDTO> createMaintenance(@RequestBody MaintenanceRequestDTO dto) {
        return ResponseEntity.ok(housekeepingService.createMaintenanceRequest(dto));
    }

    @GetMapping("/maintenance")
    public ResponseEntity<List<MaintenanceRequestDTO>> getAllMaintenance() {
        return ResponseEntity.ok(housekeepingService.getAllMaintenanceRequests());
    }

    @PutMapping("/maintenance/{id}")
    public ResponseEntity<MaintenanceRequestDTO> updateMaintenance(@PathVariable Long id,
                                                                   @RequestBody MaintenanceRequestDTO dto) {
        if(dto.getStatus() != null){
            return ResponseEntity.ok(housekeepingService.updateMaintenanceStatus(id, dto.getStatus().name()));
        }
        return ResponseEntity.ok(dto); // fallback
    }

    @DeleteMapping("/maintenance/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        housekeepingService.deleteMaintenanceRequest(id);
        return ResponseEntity.noContent().build();
    }



    // ===== Asset Maintenance Logs =====
    @PostMapping("/asset-maintenance")
    public ResponseEntity<AssetMaintenanceLogDTO> createAssetLog(@RequestBody AssetMaintenanceLogDTO dto) {
        return ResponseEntity.ok(housekeepingService.createAssetMaintenanceLog(dto));
    }

    @GetMapping("/asset-maintenance")
    public ResponseEntity<List<AssetMaintenanceLogDTO>> getAllAssetLogs() {
        return ResponseEntity.ok(housekeepingService.getAllAssetMaintenanceLogs());
    }

    // ✅ Update Asset Log
    @PutMapping("/asset-maintenance/{id}")
    public ResponseEntity<AssetMaintenanceLogDTO> updateAssetLog(@PathVariable Long id,
                                                                 @RequestBody AssetMaintenanceLogDTO dto) {
        return ResponseEntity.ok(housekeepingService.updateAssetLog(id, dto));
    }

    // ✅ Delete Asset Log
    @DeleteMapping("/asset-maintenance/{id}")
    public ResponseEntity<Void> deleteAssetLog(@PathVariable Long id) {
        housekeepingService.deleteAssetLog(id);
        return ResponseEntity.noContent().build();
    }




    // ===== Housekeeping Statistics =====
    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')") // optional: only admin can access
    public ResponseEntity<HousekeepingStatsDTO> getHousekeepingStats() {
        HousekeepingStatsDTO stats = housekeepingService.getHousekeepingStats();
        return ResponseEntity.ok(stats);
    }
}
