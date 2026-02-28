package com.My.Spring_Final_Project.Service.dashboard;

import com.My.Spring_Final_Project.DTO.dashboard.DashboardResponseDTO;
import com.My.Spring_Final_Project.Service.booking.BookingService;
import com.My.Spring_Final_Project.Service.customer.CustomerService;
import com.My.Spring_Final_Project.Service.hotel.HotelService;
import com.My.Spring_Final_Project.Service.housekeeping.HousekeepingService;
import com.My.Spring_Final_Project.Service.inventory.interfaces.InventoryService;
import com.My.Spring_Final_Project.Service.payment.PaymentService;
import com.My.Spring_Final_Project.Service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final HousekeepingService housekeepingService;
    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @Override
    public DashboardResponseDTO getDashboard() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();

        Map<String, Object> sections = new HashMap<>();

        // ===== ADMIN =====
        if (role.equals("ROLE_ADMIN")) {
            sections.put("bookings", bookingService.getBookingStats());
            sections.put("rooms", roomService.getRoomStats());
            sections.put("payments", paymentService.getPaymentStats());
            sections.put("customers", customerService.getCustomerStats());
            sections.put("housekeeping", housekeepingService.getHousekeepingStats());
            sections.put("inventory", inventoryService.getInventoryStats());
            sections.put("hotel", hotelService.getHotelStats());
        }

        // ===== MODERATOR =====
        else if (role.equals("ROLE_MODERATOR")) {
            sections.put("bookings", bookingService.getBookingStats());
            sections.put("rooms", roomService.getRoomStats());
            sections.put("housekeeping", housekeepingService.getHousekeepingStats());
        }

        // ===== USER =====
        else {
            sections.put("myBookings", bookingService.getMyBookingStats());
            sections.put("myInvoices", paymentService.getMyInvoiceStats());
        }

        return DashboardResponseDTO.builder()
                .role(role)
                .generatedAt(LocalDateTime.now())
                .sections(sections)
                .build();
    }
}
