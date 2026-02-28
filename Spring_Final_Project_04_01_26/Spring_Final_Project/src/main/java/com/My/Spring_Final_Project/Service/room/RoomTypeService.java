package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomTypeDTO;
import com.My.Spring_Final_Project.Entity.room.RoomType;

import java.util.List;

public interface RoomTypeService {
    RoomType create(RoomTypeDTO dto);
    List<RoomType> getAll();
}
