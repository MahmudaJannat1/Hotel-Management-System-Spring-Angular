package com.My.Spring_Final_Project.DTO.admin;

import com.My.Spring_Final_Project.Entity.auth.Role;
import lombok.Data;
import java.util.List;

@Data
public class AdminStatisticsDTO {

    // Users
    private Long totalUsers;
    private Long enabledUsers;
    private List<Role> roles;

    // Bookings
    private Long totalBookings;
    private Long pendingBookings;
    private Long cancelledBookings;
    private Long completedBookings;

    // Revenue
    private Double totalRevenue;

    // Customers
    private Long totalCustomers;

    // Rooms
    private Long totalRooms;           // active rooms
    private Long activeRooms;
    private Long occupiedRooms;

    // Hotels
    private Long totalHotels;
    private Long activeHotels;
    private Long totalRoomsInHotels;
    private Long occupiedRoomsInHotels;

}
