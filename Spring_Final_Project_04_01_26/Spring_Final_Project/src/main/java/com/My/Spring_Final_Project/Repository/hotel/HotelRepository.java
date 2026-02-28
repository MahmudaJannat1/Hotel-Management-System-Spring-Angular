package com.My.Spring_Final_Project.Repository.hotel;

import com.My.Spring_Final_Project.Entity.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Long countByIsActiveTrue();

    @Query("SELECT COUNT(r) FROM Room r WHERE r.hotel.isActive = true AND r.isActive = true")
    Long countTotalRooms();

    @Query("SELECT COUNT(r) FROM Room r WHERE r.status = com.My.Spring_Final_Project.Enum.room.RoomStatus.OCCUPIED AND r.hotel.isActive = true AND r.isActive = true")
    Long countOccupiedRooms();

}
