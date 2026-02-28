package com.My.Spring_Final_Project.Entity.event;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Event.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "event_bookings")
public class EventBooking extends SoftDeleteEntity {
    @ManyToOne
    private EventHall hall;

    @ManyToOne
    private EventPackage eventPackage;

    @Enumerated(EnumType.STRING)
    private EventStatus status;  // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime eventDate;
}

