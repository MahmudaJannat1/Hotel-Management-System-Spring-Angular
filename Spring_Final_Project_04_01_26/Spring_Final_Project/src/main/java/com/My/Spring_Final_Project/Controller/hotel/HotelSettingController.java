package com.My.Spring_Final_Project.Controller.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelSettingDTO;
import com.My.Spring_Final_Project.Service.hotel.HotelSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotel-settings")
@RequiredArgsConstructor
public class HotelSettingController {

    private final HotelSettingService settingService;

    @PostMapping
    public ResponseEntity<HotelSettingDTO> create(@RequestBody HotelSettingDTO dto) {
        return ResponseEntity.ok(settingService.createSetting(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelSettingDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(settingService.getSetting(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<HotelSettingDTO>> getByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(settingService.getSettingsByHotel(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelSettingDTO> update(@PathVariable Long id, @RequestBody HotelSettingDTO dto) {
        return ResponseEntity.ok(settingService.updateSetting(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
