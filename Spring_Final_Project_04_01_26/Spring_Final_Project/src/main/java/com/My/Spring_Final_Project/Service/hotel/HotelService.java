// HotelService
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelDTO;
import com.My.Spring_Final_Project.DTO.hotel.HotelStatsDTO;

import java.util.List;

public interface HotelService {
    HotelDTO createHotel(HotelDTO dto);
    HotelDTO getHotel(Long id);
    List<HotelDTO> getAllHotels();
    HotelDTO updateHotel(Long id, HotelDTO dto);
    void deleteHotel(Long id);
    HotelStatsDTO getHotelStats();

}
