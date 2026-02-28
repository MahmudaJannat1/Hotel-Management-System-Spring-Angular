package com.My.Spring_Final_Project.Service.inventory.interfaces;

import com.My.Spring_Final_Project.DTO.inventory.StockItemDTO;
import java.util.List;

public interface StockItemService {
    StockItemDTO createStockItem(StockItemDTO dto);
    StockItemDTO getStockItem(Long id);
    List<StockItemDTO> getAllStockItems();
    StockItemDTO updateStockItem(Long id, StockItemDTO dto);
    void deleteStockItem(Long id); // soft delete
}
