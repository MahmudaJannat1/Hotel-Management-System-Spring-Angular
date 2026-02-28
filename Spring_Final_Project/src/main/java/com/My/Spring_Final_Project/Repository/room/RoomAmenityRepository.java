package com.My.Spring_Final_Project.Repository.room;

import com.My.Spring_Final_Project.Entity.room.RoomAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, Long> {
    Optional<RoomAmenity> findByName(String name);
}
