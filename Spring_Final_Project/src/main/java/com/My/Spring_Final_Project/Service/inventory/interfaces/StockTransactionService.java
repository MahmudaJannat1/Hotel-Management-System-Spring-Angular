package com.My.Spring_Final_Project.Service.inventory.interfaces;

import com.My.Spring_Final_Project.DTO.inventory.StockTransactionDTO;
import java.util.List;

public interface StockTransactionService {
    StockTransactionDTO createTransaction(StockTransactionDTO dto);
    StockTransactionDTO getTransaction(Long id);
    List<StockTransactionDTO> getTransactionsByStockItem(Long stockItemId);
}
