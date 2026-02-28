package com.My.Spring_Final_Project.DTO.room;

import lombok.Data;

@Data
public class RoomStatsDTO {

    private long totalRooms;
    private long availableRooms;
    private long occupiedRooms;
    private long maintenanceRooms;
    private long bookedRooms;
}
