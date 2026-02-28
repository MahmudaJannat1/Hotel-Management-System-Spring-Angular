package com.My.Spring_Final_Project.Controller.booking;

import com.My.Spring_Final_Project.DTO.booking.BookingRequestDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingResponseDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingStatsDTO;
import com.My.Spring_Final_Project.Enum.booking.BookingItemType;
import com.My.Spring_Final_Project.Service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    // Create a new booking
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BookingResponseDTO> create(@RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    // Get booking by booking code
    @GetMapping("/{code}")
    public ResponseEntity<BookingResponseDTO> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(bookingService.getBookingByCode(code));
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAll() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Update a booking
    @PutMapping("/{code}")
    public ResponseEntity<BookingResponseDTO> update(
            @PathVariable String code,
            @RequestBody BookingRequestDTO request
    ) {
        return ResponseEntity.ok(bookingService.updateBooking(code, request));
    }

    // Cancel a booking (Soft delete)
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> cancel(@PathVariable String code) {
        bookingService.cancelBooking(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/booking-stats")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<BookingStatsDTO> getBookingStats() {
        BookingStatsDTO stats = bookingService.getBookingStats();
        return ResponseEntity.ok(stats);
    }



//    @GetMapping("/items")
//    public List<String> getBookingItemDropdown() {
//        return Arrays.stream(BookingItemType.values())
//                .map(Enum::name)
//                .toList();
//    }


    @GetMapping("/items")
    public List<String> getBookingItemDropdown() {
        return Arrays.stream(BookingItemType.values())
                .map(Enum::name)
                .toList();
    }




}
