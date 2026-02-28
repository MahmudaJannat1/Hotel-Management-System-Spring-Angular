// HotelServiceImpl
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelDTO;
import com.My.Spring_Final_Project.DTO.hotel.HotelStatsDTO;
import com.My.Spring_Final_Project.Entity.hotel.Hotel;
import com.My.Spring_Final_Project.Repository.hotel.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createHotel(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotelRepository.save(hotel);
        dto.setId(hotel.getId());
        return dto;
    }

    @Override
    public HotelDTO getHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return mapToDTO(hotel);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotelRepository.save(hotel);
        return mapToDTO(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setIsActive(false); // Soft delete
        hotelRepository.save(hotel);
    }

    private HotelDTO mapToDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        return dto;
    }

    @Override
    public HotelStatsDTO getHotelStats() {
        HotelStatsDTO dto = new HotelStatsDTO();
        dto.setTotalHotels(hotelRepository.count());
        dto.setActiveHotels(hotelRepository.countByIsActiveTrue());
        dto.setTotalRooms(hotelRepository.countTotalRooms());
        dto.setOccupiedRooms(hotelRepository.countOccupiedRooms());
        return dto;
    }
}

