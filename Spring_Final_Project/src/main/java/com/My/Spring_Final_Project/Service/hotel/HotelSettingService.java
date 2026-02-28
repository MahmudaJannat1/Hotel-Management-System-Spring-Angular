// HotelSettingService
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelSettingDTO;
import java.util.List;

public interface HotelSettingService {
    HotelSettingDTO createSetting(HotelSettingDTO dto);
    HotelSettingDTO getSetting(Long id);
    List<HotelSettingDTO> getSettingsByHotel(Long hotelId);
    HotelSettingDTO updateSetting(Long id, HotelSettingDTO dto);
    void deleteSetting(Long id);
}
