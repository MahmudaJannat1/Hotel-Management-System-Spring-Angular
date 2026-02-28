package com.My.Spring_Final_Project.Controller.booking;

import com.My.Spring_Final_Project.DTO.booking.BookingRequestDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingResponseDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingStatsDTO;
import com.My.Spring_Final_Project.Service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminBookingController {

    private final BookingService bookingService;

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAll() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Get booking by code
    @GetMapping("/{code}")
    public ResponseEntity<BookingResponseDTO> get(@PathVariable String code) {
        return ResponseEntity.ok(bookingService.getBookingByCode(code));
    }

    // Update booking
    @PutMapping("/{code}")
    public ResponseEntity<BookingResponseDTO> update(
            @PathVariable String code,
            @RequestBody BookingRequestDTO request
    ) {
        return ResponseEntity.ok(bookingService.updateBooking(code, request));
    }

    // Cancel booking (Soft delete)
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> cancel(@PathVariable String code) {
        bookingService.cancelBooking(code);
        return ResponseEntity.noContent().build();
    }

    // Booking Stats
    @GetMapping("/stats")
    public ResponseEntity<BookingStatsDTO> getStats() {
        return ResponseEntity.ok(bookingService.getBookingStats());
    }
}
