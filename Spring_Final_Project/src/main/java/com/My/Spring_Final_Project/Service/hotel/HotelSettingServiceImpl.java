// HotelSettingServiceImpl
package com.My.Spring_Final_Project.Service.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelSettingDTO;
import com.My.Spring_Final_Project.Entity.hotel.Hotel;
import com.My.Spring_Final_Project.Entity.hotel.HotelSetting;
import com.My.Spring_Final_Project.Repository.hotel.HotelRepository;
import com.My.Spring_Final_Project.Repository.hotel.HotelSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelSettingServiceImpl implements HotelSettingService {

    private final HotelSettingRepository settingRepository;
    private final HotelRepository hotelRepository;

    @Override
    public HotelSettingDTO createSetting(HotelSettingDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        HotelSetting setting = new HotelSetting();
        setting.setSettingName(dto.getSettingName());
        setting.setSettingValue(dto.getSettingValue());
        setting.setHotel(hotel);
        settingRepository.save(setting);
        dto.setId(setting.getId());
        return dto;
    }

    @Override
    public HotelSettingDTO getSetting(Long id) {
        HotelSetting setting = settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found"));
        return mapToDTO(setting);
    }

    @Override
    public List<HotelSettingDTO> getSettingsByHotel(Long hotelId) {
        return settingRepository.findByHotelId(hotelId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelSettingDTO updateSetting(Long id, HotelSettingDTO dto) {
        HotelSetting setting = settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found"));
        setting.setSettingName(dto.getSettingName());
        setting.setSettingValue(dto.getSettingValue());
        settingRepository.save(setting);
        return mapToDTO(setting);
    }

    @Override
    public void deleteSetting(Long id) {
        HotelSetting setting = settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found"));
        setting.setIsActive(false); // Soft delete
        settingRepository.save(setting);
    }

    private HotelSettingDTO mapToDTO(HotelSetting setting) {
        HotelSettingDTO dto = new HotelSettingDTO();
        dto.setId(setting.getId());
        dto.setSettingName(setting.getSettingName());
        dto.setSettingValue(setting.getSettingValue());
        dto.setHotelId(setting.getHotel().getId());
        return dto;
    }
}
