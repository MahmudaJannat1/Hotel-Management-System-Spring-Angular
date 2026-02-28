package com.My.Spring_Final_Project.Controller.inventory;

import com.My.Spring_Final_Project.DTO.inventory.StockTransactionDTO;
import com.My.Spring_Final_Project.Service.inventory.interfaces.StockTransactionService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/transactions")
@RequiredArgsConstructor
public class StockTransactionController {

    private final StockTransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StockTransactionDTO> create(@RequestBody StockTransactionDTO dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockTransactionDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @GetMapping("/stock/{stockItemId}")
    public ResponseEntity<List<StockTransactionDTO>> getByStockItem(
            @PathVariable Long stockItemId) {
        return ResponseEntity.ok(transactionService.getTransactionsByStockItem(stockItemId));
    }
}
