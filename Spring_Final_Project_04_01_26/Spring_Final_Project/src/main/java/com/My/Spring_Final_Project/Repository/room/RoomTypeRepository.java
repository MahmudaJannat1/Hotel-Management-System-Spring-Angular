package com.My.Spring_Final_Project.Repository.room;

import com.My.Spring_Final_Project.Entity.room.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    Optional<RoomType> findByNameAndIsActiveTrue(String name);

    List<RoomType> findByIsActiveTrue();

    Optional<RoomType> findByName(String name);





}
