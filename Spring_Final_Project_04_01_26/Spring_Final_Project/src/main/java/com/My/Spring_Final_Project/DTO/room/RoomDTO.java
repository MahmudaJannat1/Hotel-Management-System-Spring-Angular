package com.My.Spring_Final_Project.DTO.room;

import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomDTO {
    private String roomNumber;
    private Long roomTypeId;
    private RoomStatus status;
    private String floor;
    private List<String> imageUrls;  // ✅ must be List<String>
    private List<String> amenitiesNames;  // List of amenity names


    // 🔹 ADDED fields (for rich room list)
    private String roomTypeName;        // Tropical Garden View Room
    private Integer sizeSqm;             // 48 sqm
    private Integer maxGuests;           // 2 guests
    private Integer adults;              // Occupancy: 2 adults
    private BigDecimal pricePerNight;    // Optional (future)
    private String shortDescription;     // Hotel-style description
}
