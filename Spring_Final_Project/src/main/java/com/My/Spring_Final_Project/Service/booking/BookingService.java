package com.My.Spring_Final_Project.Service.booking;

import com.My.Spring_Final_Project.DTO.booking.BookingRequestDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingResponseDTO;
import com.My.Spring_Final_Project.DTO.booking.BookingStatsDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingByCode(String bookingCode);

    List<BookingResponseDTO> getAllBookings();

    BookingResponseDTO updateBooking(String bookingCode, BookingRequestDTO request);

    void cancelBooking(String bookingCode);

    //-----BookingStats-------
    BookingStatsDTO getBookingStats();

    BookingStatsDTO getMyBookingStats();

}
