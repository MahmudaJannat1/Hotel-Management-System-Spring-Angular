// HotelSettingDTO
package com.My.Spring_Final_Project.DTO.hotel;

import lombok.Data;

@Data
public class HotelSettingDTO {
    private Long id;
    private String settingName;
    private String settingValue;
    private Long hotelId; // reference to Hotel
}
