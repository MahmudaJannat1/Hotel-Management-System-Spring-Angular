package com.My.Spring_Final_Project.Repository.room;

import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // ---------------- BASIC ----------------

    Optional<Room> findByRoomNumberAndIsActiveTrue(String roomNumber);

    List<Room> findByIsActiveTrue();

    Long countByIsActiveTrue();

    long countByStatus(RoomStatus status);

    long countByStatusAndIsActiveTrue(RoomStatus status);

    long countByHotelIdAndIsActiveTrue(Long hotelId);

    long countByHotelIdAndStatus(Long hotelId, RoomStatus status);

    // ---------------- IMPORTANT FIX ----------------
    // Prevent Hibernate duplicate Room issue
    @Query("""
        SELECT DISTINCT r 
        FROM Room r 
        LEFT JOIN FETCH r.images
        WHERE r.isActive = true
    """)
    List<Room> findAllDistinctActive();

//    @Query("SELECT r.roomType FROM Room r WHERE r.name = :name")
//    Optional<RoomType> findByName(String name);

    Optional<RoomType> findByName(String name);



}
