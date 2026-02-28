package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.AssetMaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AssetMaintenanceLogRepository extends JpaRepository<AssetMaintenanceLog, Long> {

    List<AssetMaintenanceLog> findByMaintenanceDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<AssetMaintenanceLog> findByIsActiveTrue();

    Long countByIsActiveTrue();                        // ✅ for stats

}
