package com.My.Spring_Final_Project.Entity.housekeeping;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.hotel.HotelStaff;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "housekeeping_staff_assignments")
public class HousekeepingStaffAssignment extends SoftDeleteEntity {

    @ManyToOne(optional = false)
    private HousekeepingTask task;

    @ManyToOne(optional = false)
    private HotelStaff staff;

    private LocalDateTime assignedAt;
}
