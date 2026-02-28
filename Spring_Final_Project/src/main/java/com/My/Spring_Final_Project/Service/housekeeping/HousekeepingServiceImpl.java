package com.My.Spring_Final_Project.Service.housekeeping;

import com.My.Spring_Final_Project.DTO.housekeeping.*;
import com.My.Spring_Final_Project.Entity.housekeeping.*;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.*;
import com.My.Spring_Final_Project.Repository.housekeeping.*;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HousekeepingServiceImpl implements HousekeepingService {

    private final HousekeepingTaskRepository taskRepository;
    private final LaundryRequestRepository laundryRepository;
    private final MaintenanceRequestRepository maintenanceRepository;
    private final AssetMaintenanceLogRepository assetLogRepository;
    private final RoomRepository roomRepository;

    // ================= TASK =================
    @Override
    public HousekeepingTaskDTO createTask(HousekeepingTaskDTO dto) {
        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        HousekeepingTask task = new HousekeepingTask();
        task.setRoom(room);
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus() == null ? TaskStatus.PENDING : dto.getStatus());
        task.setAssignedTo(dto.getAssignedTo());

        taskRepository.save(task);
        dto.setId(task.getId());
        return dto;
    }

    @Override
    public HousekeepingTaskDTO getTask(Long id) {
        return mapTask(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found")));
    }

    @Override
    public List<HousekeepingTaskDTO> getTasksByRoom(Long roomId) {
        return taskRepository.findByRoomIdAndIsActiveTrue(roomId)
                .stream().map(this::mapTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<HousekeepingTaskDTO> getAllTasks() {
        return taskRepository.findByIsActiveTrue()
                .stream().map(this::mapTask)
                .collect(Collectors.toList());
    }

    @Override
    public HousekeepingTaskDTO updateTaskStatus(Long id, String status) {
        HousekeepingTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(TaskStatus.valueOf(status));
        return mapTask(task);
    }

    @Override
    public HousekeepingTaskDTO updateTask(Long id, HousekeepingTaskDTO dto) {
        HousekeepingTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) task.setStatus(dto.getStatus());
        if (dto.getAssignedTo() != null) task.setAssignedTo(dto.getAssignedTo());

        taskRepository.save(task);
        return mapTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        HousekeepingTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setIsActive(false);
    }

    // ================= LAUNDRY =================
    @Override
    public LaundryRequestDTO createLaundryRequest(LaundryRequestDTO dto) {
        LaundryRequest req = new LaundryRequest();
        req.setDescription(dto.getDescription());
        req.setStatus(LaundryStatus.PENDING);
        laundryRepository.save(req);
        dto.setId(req.getId());
        return dto;
    }

    @Override
    public List<LaundryRequestDTO> getAllLaundryRequests() {
        return laundryRepository.findByIsActiveTrue()
                .stream().map(this::mapLaundry)
                .collect(Collectors.toList());
    }

    @Override
    public LaundryRequestDTO updateLaundryStatus(Long id, String status) {
        LaundryRequest req = laundryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laundry request not found"));
        req.setStatus(LaundryStatus.valueOf(status));
        laundryRepository.save(req); // ✅ Save change
        return mapLaundry(req);
    }

    @Override
    public void deleteLaundryRequest(Long id) {
        LaundryRequest req = laundryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laundry request not found"));
        laundryRepository.delete(req); // ✅ Hard delete
    }


    // ================= MAINTENANCE =================
    @Override
    public MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO dto) {
        MaintenanceRequest req = new MaintenanceRequest();
        req.setDescription(dto.getDescription());
        req.setStatus(MaintenanceStatus.PENDING);
        maintenanceRepository.save(req);
        dto.setId(req.getId());
        return dto;
    }

    @Override
    public List<MaintenanceRequestDTO> getAllMaintenanceRequests() {
        return maintenanceRepository.findByIsActiveTrue()
                .stream().map(this::mapMaintenance)
                .collect(Collectors.toList());
    }
    @Override
    public MaintenanceRequestDTO updateMaintenanceStatus(Long id, String status) {
        MaintenanceRequest req = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found"));
        req.setStatus(MaintenanceStatus.valueOf(status));
        maintenanceRepository.save(req); // ✅ Save change
        return mapMaintenance(req);
    }


    @Override
    public void deleteMaintenanceRequest(Long id) {
        MaintenanceRequest req = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found"));
        maintenanceRepository.delete(req); // ✅ Hard delete
    }

    // ================= ASSET LOG =================
    @Override
    public AssetMaintenanceLogDTO createAssetMaintenanceLog(AssetMaintenanceLogDTO dto) {
        AssetMaintenanceLog log = new AssetMaintenanceLog();
        log.setAssetName(dto.getAssetName());
        log.setDescription(dto.getDescription());
        log.setMaintenanceDate(LocalDateTime.now());
        assetLogRepository.save(log);
        dto.setId(log.getId());
        return dto;
    }

    @Override
    public List<AssetMaintenanceLogDTO> getAllAssetMaintenanceLogs() {
        return assetLogRepository.findByIsActiveTrue()
                .stream().map(this::mapAssetLog)
                .collect(Collectors.toList());
    }

    // ================= ASSET LOG (UPDATE & DELETE) =================
    @Override
    public AssetMaintenanceLogDTO updateAssetLog(Long id, AssetMaintenanceLogDTO dto) {
        AssetMaintenanceLog log = assetLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset maintenance log not found"));

        if (dto.getAssetName() != null) log.setAssetName(dto.getAssetName());
        if (dto.getDescription() != null) log.setDescription(dto.getDescription());
        if (dto.getMaintenanceDate() != null) log.setMaintenanceDate(dto.getMaintenanceDate());

        assetLogRepository.save(log); // ✅ Save changes
        return mapAssetLog(log);
    }

    @Override
    public void deleteAssetLog(Long id) {
        AssetMaintenanceLog log = assetLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset maintenance log not found"));
        assetLogRepository.delete(log); // ✅ Hard delete
    }


    // ================= HOUSEKEEPING STATS =================
    @Override
    public HousekeepingStatsDTO getHousekeepingStats() {
        HousekeepingStatsDTO stats = new HousekeepingStatsDTO();

        // ===== Tasks =====
        stats.setTotalTasks(taskRepository.countByIsActiveTrue());
        stats.setPendingTasks(taskRepository.countByStatus(TaskStatus.PENDING));
        stats.setInProgressTasks(taskRepository.countByStatus(TaskStatus.IN_PROGRESS));
        stats.setCompletedTasks(taskRepository.countByStatus(TaskStatus.COMPLETED));

        // ===== Laundry =====
        stats.setTotalLaundryRequests(laundryRepository.countByIsActiveTrue());
        stats.setPendingLaundryRequests(laundryRepository.countByStatus(LaundryStatus.PENDING));
        stats.setDoneLaundryRequests(laundryRepository.countByStatus(LaundryStatus.DONE));

        // ===== Maintenance =====
        stats.setTotalMaintenanceRequests(maintenanceRepository.countByIsActiveTrue());
        stats.setPendingMaintenanceRequests(maintenanceRepository.countByStatus(MaintenanceStatus.PENDING));
        stats.setDoneMaintenanceRequests(maintenanceRepository.countByStatus(MaintenanceStatus.DONE));

        // ===== Asset Maintenance Logs =====
        stats.setTotalAssetMaintenanceLogs(assetLogRepository.countByIsActiveTrue());

        // ===== TOTAL =====
        stats.setTotalStats(
                stats.getTotalTasks() +
                        stats.getTotalLaundryRequests() +
                        stats.getTotalMaintenanceRequests() +
                        stats.getTotalAssetMaintenanceLogs()
        );


        return stats;
    }

    // ================= MAPPERS =================
    private HousekeepingTaskDTO mapTask(HousekeepingTask t) {
        HousekeepingTaskDTO dto = new HousekeepingTaskDTO();
        dto.setId(t.getId());
        dto.setRoomId(t.getRoom().getId());
        dto.setDescription(t.getDescription());
        dto.setStatus(t.getStatus());
        dto.setAssignedTo(t.getAssignedTo());
        return dto;
    }

    private LaundryRequestDTO mapLaundry(LaundryRequest r) {
        LaundryRequestDTO dto = new LaundryRequestDTO();
        dto.setId(r.getId());
        dto.setDescription(r.getDescription());
        dto.setStatus(r.getStatus());
        return dto;
    }

    private MaintenanceRequestDTO mapMaintenance(MaintenanceRequest r) {
        MaintenanceRequestDTO dto = new MaintenanceRequestDTO();
        dto.setId(r.getId());
        dto.setDescription(r.getDescription());
        dto.setStatus(r.getStatus());
        return dto;
    }

    private AssetMaintenanceLogDTO mapAssetLog(AssetMaintenanceLog l) {
        AssetMaintenanceLogDTO dto = new AssetMaintenanceLogDTO();
        dto.setId(l.getId());
        dto.setAssetName(l.getAssetName());
        dto.setDescription(l.getDescription());
        dto.setMaintenanceDate(l.getMaintenanceDate());
        return dto;
    }
}
