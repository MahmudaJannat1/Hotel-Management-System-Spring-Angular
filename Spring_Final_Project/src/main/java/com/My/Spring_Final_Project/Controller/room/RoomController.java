package com.My.Spring_Final_Project.Controller.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Room> create(
            @RequestPart("room") RoomDTO dto,   // 🔥 dto → room
            @RequestPart(value = "images", required = false) MultipartFile[] file) {

        Room saved = roomService.create(dto, file);
        return ResponseEntity.ok(saved);
    }




    // ⚠️ GET single (later DTO করবো)
    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.get(id));
    }

    // ⚠️ GET all (later DTO করবো)
    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

// ✅ UPDATE with optional image upload
@PutMapping("/{id}")
public ResponseEntity<String> update(
        @PathVariable Long id,
        @RequestPart("room") RoomDTO dto,
        @RequestPart(value = "images", required = false) MultipartFile[] file) {

    roomService.update(id, dto, file);
    return ResponseEntity.ok("Room updated successfully");
}



    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ STATS
    @GetMapping("/room-stats")
    public ResponseEntity<RoomStatsDTO> getRoomStats() {
        return ResponseEntity.ok(roomService.getRoomStats());
    }
}
