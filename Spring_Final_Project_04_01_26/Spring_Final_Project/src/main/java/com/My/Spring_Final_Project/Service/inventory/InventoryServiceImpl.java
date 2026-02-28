package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.InventoryStatsDTO;
import com.My.Spring_Final_Project.Repository.inventory.AssetRepository;
import com.My.Spring_Final_Project.Repository.inventory.StockItemRepository;
import com.My.Spring_Final_Project.Repository.inventory.StockTransactionRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final AssetRepository assetRepository;
    private final StockItemRepository stockItemRepository;
    private final StockTransactionRepository transactionRepository;

    @Override
    public InventoryStatsDTO getInventoryStats() {
        InventoryStatsDTO stats = new InventoryStatsDTO();

        stats.setTotalAssets(assetRepository.countByIsActiveTrue());
        stats.setAllocatedAssets(assetRepository.countAllocatedAssets());
        stats.setAvailableAssets(stats.getTotalAssets() - stats.getAllocatedAssets());

        stats.setTotalStockItems(stockItemRepository.countByIsActiveTrue());
        stats.setLowStockItems(stockItemRepository.countLowStockItems());
        stats.setTotalTransactions(transactionRepository.countByIsActiveTrue());

        return stats;
    }
}
