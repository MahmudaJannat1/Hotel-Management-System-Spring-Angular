package com.My.Spring_Final_Project.Repository.booking;

import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.booking.BookingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {

    List<BookingItem> findByBooking(Booking booking);

    List<BookingItem> findByBookingAndIsActiveTrue(Booking booking);
}
