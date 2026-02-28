package com.My.Spring_Final_Project.Service.inventory.interfaces;

import com.My.Spring_Final_Project.DTO.inventory.AssetCategoryDTO;
import java.util.List;

public interface AssetCategoryService {
    AssetCategoryDTO createCategory(AssetCategoryDTO dto);
    AssetCategoryDTO getCategory(Long id);
    List<AssetCategoryDTO> getAllCategories();
    AssetCategoryDTO updateCategory(Long id, AssetCategoryDTO dto);
    void deleteCategory(Long id); // soft delete
}
