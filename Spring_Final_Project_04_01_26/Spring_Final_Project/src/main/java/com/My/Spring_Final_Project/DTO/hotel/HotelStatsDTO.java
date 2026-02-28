package com.My.Spring_Final_Project.DTO.hotel;

import lombok.*;

@Data
@Setter
@Getter

public class HotelStatsDTO {
    private Long totalHotels;
    private Long activeHotels;
    private Long totalRooms;
    private Long occupiedRooms;
}
