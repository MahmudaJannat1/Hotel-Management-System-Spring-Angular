package com.My.Spring_Final_Project.Controller.room;

import com.My.Spring_Final_Project.DTO.room.RoomTypeDTO;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Service.room.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    // CREATE
    @PostMapping
    public ResponseEntity<RoomType> create(@RequestBody RoomTypeDTO dto) {
        return ResponseEntity.ok(roomTypeService.create(dto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<RoomType>> getAll() {
        return ResponseEntity.ok(roomTypeService.getAll());
    }

    // GET BY ID
//    @GetMapping("/{id}")
//    public ResponseEntity<RoomType> get(@PathVariable Long id) {
//        return ResponseEntity.ok(roomTypeService.get(id));
//    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public ResponseEntity<RoomType> update(@PathVariable Long id, @RequestBody RoomTypeDTO dto) {
//        return ResponseEntity.ok(roomTypeService.update(id, dto));
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        roomTypeService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
