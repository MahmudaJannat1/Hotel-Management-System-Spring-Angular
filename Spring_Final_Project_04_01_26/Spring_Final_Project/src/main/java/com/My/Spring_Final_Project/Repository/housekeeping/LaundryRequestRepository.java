package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.LaundryRequest;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.LaundryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaundryRequestRepository extends JpaRepository<LaundryRequest, Long> {

    List<LaundryRequest> findByStatus(LaundryStatus status);

    List<LaundryRequest> findByIsActiveTrue();
    Long countByStatus(LaundryStatus status);         // ✅ for stats
    Long countByIsActiveTrue();
}
