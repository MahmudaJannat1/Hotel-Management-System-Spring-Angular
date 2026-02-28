package com.My.Spring_Final_Project.Service.admin;

import com.My.Spring_Final_Project.DTO.admin.AdminDashboardDTO;
import com.My.Spring_Final_Project.Service.booking.BookingService;
import com.My.Spring_Final_Project.Service.inventory.interfaces.InventoryService;
import com.My.Spring_Final_Project.Service.payment.InvoiceService;
import com.My.Spring_Final_Project.Service.payment.PaymentService;
import com.My.Spring_Final_Project.Service.room.RoomService;
import com.My.Spring_Final_Project.Service.customer.CustomerService;
import com.My.Spring_Final_Project.Service.housekeeping.HousekeepingService;
import com.My.Spring_Final_Project.Service.hotel.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final RoomService roomService;
    private final CustomerService customerService;
    private final HousekeepingService housekeepingService;
    private final InventoryService inventoryService;
    private final HotelService hotelService;
    private final InvoiceService invoiceService;

    @Override
    public AdminDashboardDTO getDashboardStats() {
        AdminDashboardDTO dto = new AdminDashboardDTO();

        dto.setBookingStats(bookingService.getBookingStats());
        dto.setPaymentStats(paymentService.getPaymentStats());
        dto.setRoomStats(roomService.getRoomStats());
        dto.setCustomerStats(customerService.getCustomerStats());
        dto.setHousekeepingStats(housekeepingService.getHousekeepingStats());
        dto.setInventoryStats(inventoryService.getInventoryStats());
        dto.setHotelStats(hotelService.getHotelStats());
        dto.setInvoiceStats(invoiceService.getInvoiceStats());

        return dto;
    }
}
