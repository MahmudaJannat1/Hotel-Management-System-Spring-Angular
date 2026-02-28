package com.My.Spring_Final_Project.Controller.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelStaffDTO;
import com.My.Spring_Final_Project.Service.hotel.HotelStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotel-staff")
@RequiredArgsConstructor
public class HotelStaffController {

    private final HotelStaffService staffService;

    @PostMapping
    public ResponseEntity<HotelStaffDTO> create(@RequestBody HotelStaffDTO dto) {
        return ResponseEntity.ok(staffService.createStaff(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelStaffDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.getStaff(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<HotelStaffDTO>> getByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(staffService.getStaffByHotel(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelStaffDTO> update(@PathVariable Long id, @RequestBody HotelStaffDTO dto) {
        return ResponseEntity.ok(staffService.updateStaff(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
