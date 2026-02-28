package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.HousekeepingTask;
import com.My.Spring_Final_Project.Enum.Housekeeping_Maintenance.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface HousekeepingTaskRepository extends JpaRepository<HousekeepingTask, Long> {

    List<HousekeepingTask> findByStatus(TaskStatus status);

    List<HousekeepingTask> findByRoomId(Long roomId);

    List<HousekeepingTask> findByAssignedTo(String assignedTo);

    List<HousekeepingTask> findByIsActiveTrue();

    List<HousekeepingTask> findByRoomIdAndIsActiveTrue(Long roomId);

    Long countByStatus(TaskStatus status);            // ✅ add for stats
    Long countByIsActiveTrue();
}
