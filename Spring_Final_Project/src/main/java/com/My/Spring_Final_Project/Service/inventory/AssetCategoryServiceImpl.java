package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.AssetCategoryDTO;
import com.My.Spring_Final_Project.Entity.inventory.AssetCategory;
import com.My.Spring_Final_Project.Repository.inventory.AssetCategoryRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.AssetCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetCategoryServiceImpl implements AssetCategoryService {

    private final AssetCategoryRepository categoryRepository;

    @Override
    public AssetCategoryDTO createCategory(AssetCategoryDTO dto) {
        AssetCategory category = new AssetCategory();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);
        dto.setId(category.getId());
        return dto;
    }

    @Override
    public AssetCategoryDTO getCategory(Long id) {
        AssetCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapToDTO(category);
    }

    @Override
    public List<AssetCategoryDTO> getAllCategories() {
        return categoryRepository.findByIsActiveTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AssetCategoryDTO updateCategory(Long id, AssetCategoryDTO dto) {
        AssetCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        categoryRepository.save(category);
        return mapToDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        AssetCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setIsActive(false);
        categoryRepository.save(category);
    }

    private AssetCategoryDTO mapToDTO(AssetCategory category) {
        AssetCategoryDTO dto = new AssetCategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }
}
