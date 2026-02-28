// HotelStaffService
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelStaffDTO;
import java.util.List;

public interface HotelStaffService {
    HotelStaffDTO createStaff(HotelStaffDTO dto);
    HotelStaffDTO getStaff(Long id);
    List<HotelStaffDTO> getStaffByHotel(Long hotelId);
    HotelStaffDTO updateStaff(Long id, HotelStaffDTO dto);
    void deleteStaff(Long id);
}
