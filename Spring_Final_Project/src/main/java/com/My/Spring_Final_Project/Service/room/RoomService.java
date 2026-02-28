package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    Room create(RoomDTO dto,  MultipartFile[] file);
    Room get(Long id);
    List<Room> getAll();
    Room update(Long id, RoomDTO dto,  MultipartFile[] file);
    void delete(Long id);
    RoomStatsDTO getRoomStats();

}
