package com.My.Spring_Final_Project.Controller.hotel;

import com.My.Spring_Final_Project.DTO.hotel.HotelDTO;
import com.My.Spring_Final_Project.DTO.hotel.HotelStatsDTO;
import com.My.Spring_Final_Project.Service.hotel.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> create(@RequestBody HotelDTO dto) {
        return ResponseEntity.ok(hotelService.createHotel(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAll() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> update(@PathVariable Long id, @RequestBody HotelDTO dto) {
        return ResponseEntity.ok(hotelService.updateHotel(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/admin/hotel-stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<HotelStatsDTO> getHotelStats() {
        HotelStatsDTO stats = hotelService.getHotelStats();
        return ResponseEntity.ok(stats);
    }
}
