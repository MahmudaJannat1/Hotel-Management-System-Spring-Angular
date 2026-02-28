package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.StockItemDTO;
import com.My.Spring_Final_Project.Entity.inventory.AssetCategory;
import com.My.Spring_Final_Project.Entity.inventory.StockItem;
import com.My.Spring_Final_Project.Repository.inventory.AssetCategoryRepository;
import com.My.Spring_Final_Project.Repository.inventory.StockItemRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StockItemServiceImpl implements StockItemService {

    private final StockItemRepository stockItemRepository;
    private final AssetCategoryRepository assetCategoryRepository;

    @Override
    public StockItemDTO createStockItem(StockItemDTO dto) {
        StockItem item = new StockItem();
        item.setName(dto.getName());
        item.setQuantity(dto.getQuantity());
        item.setMinQuantity(dto.getMinQuantity());

        if(dto.getCategoryId() != null) {
            AssetCategory cat = assetCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(cat);
        }

        stockItemRepository.save(item);
        return mapToDTO(item); // ✅ dto mapping
    }


    @Override
    public StockItemDTO getStockItem(Long id) {
        StockItem item = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        return mapToDTO(item);
    }

    @Override
    public List<StockItemDTO> getAllStockItems() {
        return stockItemRepository.findByIsActiveTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockItemDTO updateStockItem(Long id, StockItemDTO dto) {
        StockItem item = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));

        if(dto.getName() != null) item.setName(dto.getName());
        if(dto.getQuantity() != null) item.setQuantity(dto.getQuantity());
        if(dto.getMinQuantity() != null) item.setMinQuantity(dto.getMinQuantity());
        if(dto.getCategoryId() != null) {
            AssetCategory cat = assetCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(cat);
        }

        stockItemRepository.save(item);
        return mapToDTO(item);
    }

    @Override
    public void deleteStockItem(Long id) {
        StockItem item = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        item.setIsActive(false);
        stockItemRepository.save(item);
    }

    // -----------------------
    // Helper methods
    // -----------------------

    private StockItemDTO mapToDTO(StockItem item) {
        StockItemDTO dto = new StockItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setQuantity(item.getQuantity());
        dto.setMinQuantity(item.getMinQuantity());

        if(item.getCategory() != null) {
            dto.setCategoryId(item.getCategory().getId());
            dto.setCategoryName(item.getCategory().getName());
        }

        return dto;
    }

    private StockItem mapToEntity(StockItemDTO dto) {
        StockItem item = new StockItem();
        item.setName(dto.getName());
        item.setQuantity(dto.getQuantity());
        item.setMinQuantity(dto.getMinQuantity());

        if(dto.getCategoryId() != null) { // dto থেকে category set করো
            AssetCategory cat = assetCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(cat);
        }

        return item;
    }

}
