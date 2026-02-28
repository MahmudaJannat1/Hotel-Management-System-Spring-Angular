package com.My.Spring_Final_Project.Controller.inventory;

import com.My.Spring_Final_Project.DTO.inventory.AssetAllocationDTO;
import com.My.Spring_Final_Project.Service.inventory.interfaces.AssetAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/allocations")
@RequiredArgsConstructor
public class AssetAllocationController {

    private final AssetAllocationService allocationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AssetAllocationDTO> allocate(@RequestBody AssetAllocationDTO dto) {
        return ResponseEntity.ok(allocationService.allocateAsset(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetAllocationDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(allocationService.getAllocation(id));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetAllocationDTO>> getByAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(allocationService.getAllocationsByAsset(assetId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        allocationService.deleteAllocation(id);
        return ResponseEntity.noContent().build();
    }
}
