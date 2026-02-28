// HotelStaffRepository
package com.My.Spring_Final_Project.Repository.hotel;

import com.My.Spring_Final_Project.Entity.hotel.HotelStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelStaffRepository extends JpaRepository<HotelStaff, Long> {
    List<HotelStaff> findByHotelId(Long hotelId);
}
