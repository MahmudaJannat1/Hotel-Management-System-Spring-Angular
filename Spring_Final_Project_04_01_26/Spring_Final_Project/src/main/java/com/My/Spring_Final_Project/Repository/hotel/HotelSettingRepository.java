// HotelSettingRepository
package com.My.Spring_Final_Project.Repository.hotel;

import com.My.Spring_Final_Project.Entity.hotel.HotelSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelSettingRepository extends JpaRepository<HotelSetting, Long> {
    List<HotelSetting> findByHotelId(Long hotelId);
}
