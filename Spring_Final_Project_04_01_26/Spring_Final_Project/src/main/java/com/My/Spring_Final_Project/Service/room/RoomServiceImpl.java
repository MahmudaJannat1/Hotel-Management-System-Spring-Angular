package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomAmenity;
import com.My.Spring_Final_Project.Entity.room.RoomImage;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import com.My.Spring_Final_Project.Repository.room.RoomAmenityRepository;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import com.My.Spring_Final_Project.Repository.room.RoomTypeRepository;
import com.My.Spring_Final_Project.Service.common.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Room create(RoomDTO dto,  MultipartFile[] file) {
        // 🔹 Find RoomType
        RoomType type = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        // 🔹 Create Room entity
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(type);
        room.setStatus(dto.getStatus());
        room.setFloor(dto.getFloor());

        // 🔹 Optional fields for rich UI
        room.setPricePerNight(dto.getPricePerNight());      // if Room entity supports it
        room.setSizeSqm(dto.getSizeSqm());                  // if Room entity supports it
        room.setMaxGuests(dto.getMaxGuests());              // if Room entity supports it
        room.setAdults(dto.getAdults());                    // if Room entity supports it
        room.setShortDescription(dto.getShortDescription());// if Room entity supports it

        // 🔹 Handle file uploads
        if (file != null && file.length > 0) {
            List<RoomImage> images = Arrays.stream(file)
                    .map(f -> {
                        String filename = fileStorageService.saveFile(f);
                        RoomImage img = new RoomImage();
                        img.setFilename(filename);
                        img.setRoom(room);
                        return img;
                    })
                    .collect(Collectors.toList());
            room.setImages(images);
        }

        // 🔹 Save room first
        Room savedRoom = roomRepository.save(room);

        // 🔹 Handle amenities (assuming ManyToMany)
        if (dto.getAmenitiesNames() != null && !dto.getAmenitiesNames().isEmpty()) {
            List<RoomAmenity> amenities = dto.getAmenitiesNames().stream()
                    .map(name -> roomAmenityRepository.findByName(name) // ✅ RoomAmenityRepository ব্যবহার
                            .orElseGet(() -> {
                                RoomAmenity a = new RoomAmenity();
                                a.setName(name);
                                return roomAmenityRepository.save(a); // ✅ amenities save
                            }))
                    .collect(Collectors.toList());
            savedRoom.setAmenities(amenities);

        }



        // 🔹 Handle image URLs (assuming Image entity or simple String list)
        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
            List<RoomImage> images = dto.getImageUrls().stream()
                    .map(url -> {
                        RoomImage img = new RoomImage();
                        img.setFilename(url);   // ✅ এখানে filename set
                        img.setRoom(savedRoom);
                        return img;
                    })
                    .collect(Collectors.toList()); // ✅ mutable list
            savedRoom.setImages(images);
        }

        return roomRepository.save(savedRoom);
    }


    @Override
    public Room get(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getAll() {
        return roomRepository.findAllDistinctActive();
    }


//    @Override
//    public Room update(Long id, RoomDTO dto,  MultipartFile[] file) {
//        Room room = get(id);
//
//        if(dto.getRoomNumber() != null) room.setRoomNumber(dto.getRoomNumber());
//        if(dto.getStatus() != null) room.setStatus(dto.getStatus());
//
//        if(dto.getRoomTypeId() != null) {
//            RoomType type = roomTypeRepository.findById(dto.getRoomTypeId())
//                    .orElseThrow(() -> new RuntimeException("Room type not found"));
//            room.setRoomType(type);
//        }
//
//
//        return roomRepository.save(room);
//    }

    @Override
    public Room update(Long id, RoomDTO dto, MultipartFile[] files) {
        Room room = get(id);

        // 🔹 Update basic fields
        if (dto.getRoomNumber() != null) room.setRoomNumber(dto.getRoomNumber());
        if (dto.getStatus() != null) room.setStatus(dto.getStatus());
        if (dto.getFloor() != null) room.setFloor(dto.getFloor());
        if (dto.getPricePerNight() != null) room.setPricePerNight(dto.getPricePerNight());
        if (dto.getSizeSqm() != null) room.setSizeSqm(dto.getSizeSqm());
        if (dto.getMaxGuests() != null) room.setMaxGuests(dto.getMaxGuests());
        if (dto.getAdults() != null) room.setAdults(dto.getAdults());
        if (dto.getShortDescription() != null) room.setShortDescription(dto.getShortDescription());

        // 🔹 Update RoomType
        if (dto.getRoomTypeId() != null) {
            RoomType type = roomTypeRepository.findById(dto.getRoomTypeId())
                    .orElseThrow(() -> new RuntimeException("Room type not found"));
            room.setRoomType(type);
        }

        // 🔹 Update amenities
        if (dto.getAmenitiesNames() != null && !dto.getAmenitiesNames().isEmpty()) {
            List<RoomAmenity> amenities = dto.getAmenitiesNames().stream()
                    .map(name -> roomAmenityRepository.findByName(name)
                            .orElseGet(() -> {
                                RoomAmenity a = new RoomAmenity();
                                a.setName(name);
                                return roomAmenityRepository.save(a);
                            }))
                    .collect(Collectors.toList());
            room.setAmenities(amenities);
        }

        // 🔹 Handle new file uploads
        if (files != null && files.length > 0) {
            List<RoomImage> images = Arrays.stream(files)
                    .map(f -> {
                        String filename = fileStorageService.saveFile(f);
                        RoomImage img = new RoomImage();
                        img.setFilename(filename);
                        img.setRoom(room);
                        return img;
                    })
                    .collect(Collectors.toList());
            // Append to existing images instead of overwriting
            if (room.getImages() != null) {
                room.getImages().addAll(images);
            } else {
                room.setImages(images);
            }
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

        // Active rooms only
        stats.setTotalRooms(roomRepository.countByIsActiveTrue());
        stats.setAvailableRooms(roomRepository.countByStatus(RoomStatus.AVAILABLE));
        stats.setOccupiedRooms(roomRepository.countByStatus(RoomStatus.OCCUPIED));
        stats.setMaintenanceRooms(roomRepository.countByStatus(RoomStatus.MAINTENANCE));
        stats.setBookedRooms(roomRepository.countByStatus(RoomStatus.BOOKED));

        return stats;
    }


}
