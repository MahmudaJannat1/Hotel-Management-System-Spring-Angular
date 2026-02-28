package com.My.Spring_Final_Project.Service.room;

import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomImage;
import com.My.Spring_Final_Project.Repository.room.RoomImageRepository;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import jakarta.annotation.*;
import lombok.*;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomImageService {

    private final RoomRepository roomRepository;
    private final RoomImageRepository roomImageRepository;

    private final String uploadDir = "uploads/rooms/";

    // Upload
    public RoomImage saveImage(Long roomId, MultipartFile file) throws IOException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        RoomImage roomImage = new RoomImage();
        roomImage.setFilename(filename);
        roomImage.setRoom(room);

        return roomImageRepository.save(roomImage);
    }

    // Serve
    public Resource loadImage(String filename) throws MalformedURLException {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        return new UrlResource(filePath.toUri());
    }

    // Get all images of a room
    public List<String> getImageFilenamesByRoom(Long roomId) {
        return roomImageRepository.findByRoomId(roomId)
                .stream()
                .map(RoomImage::getFilename)
                .toList();
    }
}

