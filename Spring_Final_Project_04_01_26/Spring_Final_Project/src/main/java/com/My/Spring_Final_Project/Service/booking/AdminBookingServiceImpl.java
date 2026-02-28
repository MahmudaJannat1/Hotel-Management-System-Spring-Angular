package com.My.Spring_Final_Project.Service.booking;

import com.My.Spring_Final_Project.DTO.booking.*;
import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.booking.BookingItem;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import com.My.Spring_Final_Project.Repository.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminBookingServiceImpl implements AdminBookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDTO getBookingByCode(String code) {
        Booking booking = bookingRepository.findByBookingCode(code)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return toDTO(booking);
    }

    @Override
    public BookingResponseDTO updateBooking(String code, BookingRequestDTO request) {
        Booking booking = bookingRepository.findByBookingCode(code)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(request.getCheckInDate() != null) booking.setCheckInDate(request.getCheckInDate());
        if(request.getCheckOutDate() != null) booking.setCheckOutDate(request.getCheckOutDate());

        // Update items logic if needed

        return toDTO(bookingRepository.save(booking));
    }

    @Override
    public void cancelBooking(String code) {
        Booking booking = bookingRepository.findByBookingCode(code)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public BookingStatsDTO getBookingStats() {
        BookingStatsDTO stats = new BookingStatsDTO();
        stats.setTotalBookings(bookingRepository.count());
        stats.setPendingBookings(bookingRepository.countByStatus(BookingStatus.PENDING));
        stats.setConfirmedBookings(bookingRepository.countByStatus(BookingStatus.CONFIRMED));
        stats.setCancelledBookings(bookingRepository.countByStatus(BookingStatus.CANCELLED));
        stats.setCompletedBookings(bookingRepository.countByStatus(BookingStatus.COMPLETED));
        return stats;
    }

    private BookingResponseDTO toDTO(Booking booking) {
        List<BookingItemResponseDTO> itemDTOs = booking.getItems().stream()
                .filter(BookingItem::getIsActive)
                .map(item -> new BookingItemResponseDTO(
                        item.getItemName(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getDescription(),
                        item.getCategory()
                ))
                .collect(Collectors.toList());

        // Number of nights calculation
        int nights = 0;
        if (booking.getCheckInDate() != null && booking.getCheckOutDate() != null) {
            nights = (int) ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        }


        String roomTypeName = null;
        if (booking.getRoom() != null && booking.getRoom().getRoomType() != null) {
            roomTypeName = booking.getRoom().getRoomType().getName(); // <-- entity theke name nibo
        }
        BookingResponseDTO dto = new BookingResponseDTO(
                booking.getId(),
                booking.getBookingCode(),
                booking.getCustomer() != null ? booking.getCustomer().getId() : null,
                booking.getCustomer() != null ? booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName() : null,
                booking.getRoom() != null ? booking.getRoom().getId() : null,
                booking.getRoom() != null ? booking.getRoom().getRoomNumber() : null,
                booking.getCheckInDate() != null ? booking.getCheckInDate().toString() : null,
                booking.getCheckOutDate() != null ? booking.getCheckOutDate().toString() : null,
                booking.getStatus() != null ? booking.getStatus().name() : null,
                itemDTOs,
                booking.getAdults(),          // <-- add
                booking.getChildren(),        // <-- add
                nights,
                roomTypeName   // <-- String pathalam

        );

        return dto;
    }

}

