package com.My.Spring_Final_Project.Service.booking;

import com.My.Spring_Final_Project.DTO.booking.BookingRequestDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingResponseDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingStatsDTO;

import java.util.List;

public interface AdminBookingService {

    List<BookingResponseDTO> getAllBookings();

    BookingResponseDTO getBookingByCode(String code);

    BookingResponseDTO updateBooking(String code, BookingRequestDTO request);

    void cancelBooking(String code);

    BookingStatsDTO getBookingStats();
}
