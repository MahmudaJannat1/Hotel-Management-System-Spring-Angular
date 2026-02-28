package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.MaintenanceRequest;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.LaundryStatus;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

    List<MaintenanceRequest> findByStatus(MaintenanceStatus status);

    List<MaintenanceRequest> findByIsActiveTrue();

    Long countByStatus(MaintenanceStatus status);         // ✅ for stats
    Long countByIsActiveTrue();
}
