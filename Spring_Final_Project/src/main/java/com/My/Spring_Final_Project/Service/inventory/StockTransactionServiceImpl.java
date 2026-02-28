package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.StockTransactionDTO;
import com.My.Spring_Final_Project.Entity.inventory.StockItem;
import com.My.Spring_Final_Project.Entity.inventory.StockTransaction;
import com.My.Spring_Final_Project.Repository.inventory.StockItemRepository;
import com.My.Spring_Final_Project.Repository.inventory.StockTransactionRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StockTransactionServiceImpl implements StockTransactionService {

    private final StockTransactionRepository transactionRepository;
    private final StockItemRepository stockItemRepository;

    @Override
    public StockTransactionDTO createTransaction(StockTransactionDTO dto) {
        StockItem item = stockItemRepository.findById(dto.getStockItemId())
                .orElseThrow(() -> new RuntimeException("Stock item not found"));

        StockTransaction transaction = new StockTransaction();
        transaction.setStockItem(item);
        transaction.setQuantity(dto.getQuantity());
        transaction.setType(dto.getType());

        transactionRepository.save(transaction);
        dto.setId(transaction.getId());
        return dto;
    }

    @Override
    public StockTransactionDTO getTransaction(Long id) {
        StockTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return mapToDTO(transaction);
    }

    @Override
    public List<StockTransactionDTO> getTransactionsByStockItem(Long stockItemId) {
        return transactionRepository.findByStockItemId(stockItemId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private StockTransactionDTO mapToDTO(StockTransaction transaction) {
        StockTransactionDTO dto = new StockTransactionDTO();
        dto.setId(transaction.getId());
        dto.setStockItemId(transaction.getStockItem().getId());
        dto.setQuantity(transaction.getQuantity());
        dto.setType(transaction.getType());
        return dto;
    }
}
