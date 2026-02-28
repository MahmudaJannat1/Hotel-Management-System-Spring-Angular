package com.My.Spring_Final_Project.Controller.inventory;

import com.My.Spring_Final_Project.DTO.inventory.AssetDTO;
import com.My.Spring_Final_Project.Service.inventory.interfaces.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AssetDTO> create(@RequestBody AssetDTO dto) {
        return ResponseEntity.ok(assetService.createAsset(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAsset(id));
    }

    @GetMapping
    public ResponseEntity<List<AssetDTO>> getAll() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AssetDTO> update(
            @PathVariable Long id,
            @RequestBody AssetDTO dto) {
        return ResponseEntity.ok(assetService.updateAsset(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
