package com.My.Spring_Final_Project.Entity.room;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_status_history")
@Getter
@Setter
public class RoomStatusHistory extends SoftDeleteEntity {

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
