package com.My.Spring_Final_Project.Controller.inventory;

import com.My.Spring_Final_Project.DTO.inventory.StockItemDTO;
import com.My.Spring_Final_Project.Service.inventory.interfaces.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/stocks")
@RequiredArgsConstructor
public class StockItemController {

    private final StockItemService stockItemService;

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StockItemDTO> create(@RequestBody StockItemDTO dto) {
        return ResponseEntity.ok(stockItemService.createStockItem(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockItemDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(stockItemService.getStockItem(id));
    }

    @GetMapping
    public ResponseEntity<List<StockItemDTO>> getAll() {
        return ResponseEntity.ok(stockItemService.getAllStockItems());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StockItemDTO> update(
            @PathVariable Long id,
            @RequestBody StockItemDTO dto) {
        return ResponseEntity.ok(stockItemService.updateStockItem(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
        return ResponseEntity.noContent().build();
    }
}
