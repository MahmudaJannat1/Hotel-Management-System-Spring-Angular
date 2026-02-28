package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.HousekeepingStaffAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HousekeepingStaffAssignmentRepository
        extends JpaRepository<HousekeepingStaffAssignment, Long> {

    List<HousekeepingStaffAssignment> findByStaffId(Long staffId);

    List<HousekeepingStaffAssignment> findByTaskId(Long taskId);

    List<HousekeepingStaffAssignment> findByIsActiveTrue();
}
