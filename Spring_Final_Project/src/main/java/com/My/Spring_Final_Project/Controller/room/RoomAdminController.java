package com.My.Spring_Final_Project.Controller.room;

import com.My.Spring_Final_Project.DTO.room.RoomDTO;
import com.My.Spring_Final_Project.DTO.room.RoomStatsDTO;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Service.room.RoomAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rooms")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class RoomAdminController {

    private final RoomAdminService roomAdminService;

    // ================= CRUD =================

//    @PostMapping
//    public ResponseEntity<Room> create(@RequestBody RoomDTO dto) {
//        return ResponseEntity.ok(roomAdminService.create(dto));
//    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Room> create(
            @RequestPart("room") RoomDTO dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        return ResponseEntity.ok(roomAdminService.create(dto, images));
    }


    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomAdminService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Long id) {
        return ResponseEntity.ok(roomAdminService.get(id));
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Room> update(
            @PathVariable Long id,
            @RequestPart("room") RoomDTO dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        return ResponseEntity.ok(roomAdminService.update(id, dto, images));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomAdminService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ================= STATS =================

    @GetMapping("/stats")
    public ResponseEntity<RoomStatsDTO> getStats() {
        return ResponseEntity.ok(roomAdminService.getRoomStats());
    }
}


