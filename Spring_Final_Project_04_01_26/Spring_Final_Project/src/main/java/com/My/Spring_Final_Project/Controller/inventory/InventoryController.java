package com.My.Spring_Final_Project.Controller.inventory;

import com.My.Spring_Final_Project.DTO.inventory.InventoryStatsDTO;
import com.My.Spring_Final_Project.Service.inventory.interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')") // optional: only admin can access
    public ResponseEntity<InventoryStatsDTO> getInventoryStats() {
        InventoryStatsDTO stats = inventoryService.getInventoryStats();
        return ResponseEntity.ok(stats);
    }
}
