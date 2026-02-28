package com.My.Spring_Final_Project.Service.admin;

import com.My.Spring_Final_Project.DTO.admin.AdminStatisticsDTO;
import com.My.Spring_Final_Project.DTO.admin.AdminUserStatisticsDTO;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import com.My.Spring_Final_Project.Repository.booking.BookingRepository;
import com.My.Spring_Final_Project.Repository.customer.CustomerRepository;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import com.My.Spring_Final_Project.Repository.auth.RoleRepository;
import com.My.Spring_Final_Project.Service.hotel.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final HotelService hotelService;


    @Override
    public AdminUserStatisticsDTO getUserStatistics() {
        AdminUserStatisticsDTO dto = new AdminUserStatisticsDTO();
        dto.setTotalUsers(userRepository.count());
        dto.setEnabledUsers(userRepository.countByEnabledTrue());
        dto.setRoles(roleRepository.findAll()); // returns List<Role>
        return dto;
    }

    @Override
    public AdminStatisticsDTO getSystemStatistics() {
        AdminStatisticsDTO dto = new AdminStatisticsDTO();

        // Bookings
        dto.setTotalBookings(bookingRepository.count());
        dto.setPendingBookings(bookingRepository.countByStatus(BookingStatus.PENDING));
        dto.setCancelledBookings(bookingRepository.countByStatus(BookingStatus.CANCELLED));
        dto.setCompletedBookings(bookingRepository.countByStatus(BookingStatus.COMPLETED));

        // Revenue
        dto.setTotalRevenue(bookingRepository.sumTotalRevenue());

        // Customers
        dto.setTotalCustomers(customerRepository.countByIsActiveTrue());

        // Rooms
        dto.setTotalRooms(roomRepository.countByIsActiveTrue());
        dto.setActiveRooms(roomRepository.countByIsActiveTrue());
        dto.setOccupiedRooms(roomRepository.countByStatus(RoomStatus.OCCUPIED));

        // Hotels
        dto.setTotalHotels(hotelService.getHotelStats().getTotalHotels());
        dto.setActiveHotels(hotelService.getHotelStats().getActiveHotels());
        dto.setTotalRoomsInHotels(hotelService.getHotelStats().getTotalRooms());
        dto.setOccupiedRoomsInHotels(hotelService.getHotelStats().getOccupiedRooms());

        return dto;
    }


}
