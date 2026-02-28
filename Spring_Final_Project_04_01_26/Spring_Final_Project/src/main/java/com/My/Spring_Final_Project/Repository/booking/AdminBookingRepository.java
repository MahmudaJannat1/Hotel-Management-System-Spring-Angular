package com.My.Spring_Final_Project.Repository.booking;

import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminBookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingCode(String bookingCode);

    boolean existsByBookingCode(String bookingCode);

    Long countByStatus(BookingStatus status);

    @Query("""
    SELECT COALESCE(SUM(b.totalAmount), 0)
    FROM Booking b
    WHERE b.status = com.My.Spring_Final_Project.Enum.booking.BookingStatus.COMPLETED
    """)
    Double sumTotalRevenue();
}
