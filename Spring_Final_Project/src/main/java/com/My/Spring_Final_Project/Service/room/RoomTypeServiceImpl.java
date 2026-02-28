package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomTypeDTO;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Repository.room.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public RoomType create(RoomTypeDTO dto) {
        RoomType type = new RoomType();
        type.setName(dto.getName());
        type.setPricePerNight(dto.getPrice());
        return roomTypeRepository.save(type);
    }

    @Override
    public List<RoomType> getAll() {
        return roomTypeRepository.findByIsActiveTrue();
    }
}
