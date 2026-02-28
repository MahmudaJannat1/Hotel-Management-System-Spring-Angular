package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomAdminService {

    Room create(RoomDTO dto,  List<MultipartFile> images);

    Room get(Long id);

    List<Room> getAll();

    Room update(Long id, RoomDTO dto, List<MultipartFile> images);

    void delete(Long id);

    RoomStatsDTO getRoomStats();
}
