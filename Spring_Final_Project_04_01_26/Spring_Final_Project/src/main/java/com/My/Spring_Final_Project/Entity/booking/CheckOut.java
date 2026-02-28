package com.My.Spring_Final_Project.Entity.booking;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
public class CheckOut extends SoftDeleteEntity {

    private LocalDateTime checkOutTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String notes;
}
