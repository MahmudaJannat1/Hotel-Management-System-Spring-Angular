package com.My.Spring_Final_Project.Controller.room;

import com.My.Spring_Final_Project.Entity.room.RoomImage;
import com.My.Spring_Final_Project.Service.room.RoomImageService;
import org.springframework.core.io.Resource;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms/images")
@RequiredArgsConstructor
public class RoomImageController {

    private final RoomImageService roomImageService;

//    @PostMapping(value = "/{roomId}", consumes = "multipart/form-data") // ✅ important
//    public ResponseEntity<?> uploadImage(
//            @PathVariable Long roomId,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        RoomImage saved = roomImageService.saveImage(roomId, file);
//        return ResponseEntity.ok(Map.of("filename", saved.getFilename()));
//    }
//
//    @GetMapping("/{filename}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
//        Resource file = roomImageService.loadImage(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
//                .body(file);
//    }
//
//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<List<String>> getRoomImages(@PathVariable Long roomId) {
//        List<String> filenames = roomImageService.getImageFilenamesByRoom(roomId);
//        return ResponseEntity.ok(filenames);
//    }
}
