// HotelStaffDTO
package com.My.Spring_Final_Project.DTO.hotel;

import lombok.Data;

@Data
public class HotelStaffDTO {
    private Long id;
    private String name;
    private String position; // optional: can use enum later
    private Long hotelId; // reference to Hotel
}
