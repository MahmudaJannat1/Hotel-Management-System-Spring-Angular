package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomImage;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import com.My.Spring_Final_Project.Repository.room.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomAdminServiceImpl implements RoomAdminService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;


    @Override
    public Room create(RoomDTO dto, List<MultipartFile> images) {
        RoomType type = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(type);
        room.setStatus(dto.getStatus());
        room.setFloor(dto.getFloor());
        room.setIsActive(true);

        // Save first to get ID
        room = roomRepository.save(room);

        // Save images if exist
        if (images != null && !images.isEmpty()) {
            List<RoomImage> roomImages = saveRoomImages(images, room); // ✅ return List<RoomImage>
            room.setImages(roomImages);
            room = roomRepository.save(room);
        }

        return room;
    }


    @Override
    public Room get(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room update(Long id, RoomDTO dto, List<MultipartFile> images) {
        Room room = get(id);

        if (dto.getRoomNumber() != null)
            room.setRoomNumber(dto.getRoomNumber());

        if (dto.getStatus() != null)
            room.setStatus(dto.getStatus());

        if (dto.getFloor() != null)
            room.setFloor(dto.getFloor());

        if (dto.getRoomTypeId() != null) {
            RoomType type = roomTypeRepository.findById(dto.getRoomTypeId())
                    .orElseThrow(() -> new RuntimeException("Room type not found"));
            room.setRoomType(type);

        }

        // Save new images if any
        if (images != null && !images.isEmpty()) {
            List<RoomImage> roomImages = saveRoomImages(images, room); // ✅ return List<RoomImage>
            room.getImages().addAll(roomImages); // append to existing images
        }

        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        Room room = get(id);
        room.setIsActive(false); // soft delete
        roomRepository.save(room);
    }

    @Override
    public RoomStatsDTO getRoomStats() {
        RoomStatsDTO stats = new RoomStatsDTO();

        stats.setTotalRooms(roomRepository.countByIsActiveTrue());
        stats.setAvailableRooms(
                roomRepository.countByStatusAndIsActiveTrue(RoomStatus.AVAILABLE)
        );
        stats.setOccupiedRooms(
                roomRepository.countByStatusAndIsActiveTrue(RoomStatus.OCCUPIED)
        );
        stats.setMaintenanceRooms(
                roomRepository.countByStatusAndIsActiveTrue(RoomStatus.MAINTENANCE)
        );
        stats.setBookedRooms(
                roomRepository.countByStatusAndIsActiveTrue(RoomStatus.BOOKED)
        );

        return stats;
    }


    // ================= Helper =================

    private List<RoomImage> saveRoomImages(List<MultipartFile> images, Room room) {
        List<RoomImage> roomImages = new ArrayList<>();
        for (MultipartFile file : images) {
            try {
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/rooms/" + filename);
                Files.createDirectories(uploadPath.getParent());
                file.transferTo(uploadPath.toFile());

                RoomImage roomImage = new RoomImage();
                roomImage.setFilename(filename);
                roomImage.setRoom(room);

                roomImages.add(roomImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return roomImages;
    }

}
