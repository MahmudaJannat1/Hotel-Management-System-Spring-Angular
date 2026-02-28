package com.My.Spring_Final_Project.DTO.admin;

import com.My.Spring_Final_Project.DTO.booking.BookingStatsDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentStatsDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.DTO.customer.CustomerStatsDTO;
import com.My.Spring_Final_Project.DTO.housekeeping.HousekeepingStatsDTO;
import com.My.Spring_Final_Project.DTO.inventory.InventoryStatsDTO;
import com.My.Spring_Final_Project.DTO.hotel.HotelStatsDTO;
import com.My.Spring_Final_Project.DTO.payment.InvoiceStatsDTO;
import lombok.Data;

@Data
public class AdminDashboardDTO {

    private BookingStatsDTO bookingStats;
    private PaymentStatsDTO paymentStats;
    private RoomStatsDTO roomStats;
    private CustomerStatsDTO customerStats;
    private HousekeepingStatsDTO housekeepingStats;
    private InventoryStatsDTO inventoryStats;
    private HotelStatsDTO hotelStats;
    private InvoiceStatsDTO invoiceStats;


}
