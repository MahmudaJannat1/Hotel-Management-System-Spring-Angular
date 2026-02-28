// HotelStaffServiceImpl
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelStaffDTO;
import com.My.Spring_Final_Project.Entity.hotel.Hotel;
import com.My.Spring_Final_Project.Entity.hotel.HotelStaff;
import com.My.Spring_Final_Project.Repository.hotel.HotelRepository;
import com.My.Spring_Final_Project.Repository.hotel.HotelStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelStaffServiceImpl implements HotelStaffService {

    private final HotelStaffRepository staffRepository;
    private final HotelRepository hotelRepository;

    @Override
    public HotelStaffDTO createStaff(HotelStaffDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        HotelStaff staff = new HotelStaff();
        staff.setName(dto.getName());
        staff.setPosition(dto.getPosition());
        staff.setHotel(hotel);
        staffRepository.save(staff);
        dto.setId(staff.getId());
        return dto;
    }

    @Override
    public HotelStaffDTO getStaff(Long id) {
        HotelStaff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return mapToDTO(staff);
    }

    @Override
    public List<HotelStaffDTO> getStaffByHotel(Long hotelId) {
        return staffRepository.findByHotelId(hotelId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelStaffDTO updateStaff(Long id, HotelStaffDTO dto) {
        HotelStaff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staff.setName(dto.getName());
        staff.setPosition(dto.getPosition());
        staffRepository.save(staff);
        return mapToDTO(staff);
    }

    @Override
    public void deleteStaff(Long id) {
        HotelStaff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staff.setIsActive(false); // Soft delete
        staffRepository.save(staff);
    }

    private HotelStaffDTO mapToDTO(HotelStaff staff) {
        HotelStaffDTO dto = new HotelStaffDTO();
        dto.setId(staff.getId());
        dto.setName(staff.getName());
        dto.setPosition(staff.getPosition());
        dto.setHotelId(staff.getHotel().getId());
        return dto;
    }
}
