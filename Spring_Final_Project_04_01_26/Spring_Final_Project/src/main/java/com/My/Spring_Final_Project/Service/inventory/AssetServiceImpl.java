package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.AssetDTO;
import com.My.Spring_Final_Project.Entity.inventory.Asset;
import com.My.Spring_Final_Project.Entity.inventory.AssetCategory;
import com.My.Spring_Final_Project.Repository.inventory.AssetRepository;
import com.My.Spring_Final_Project.Repository.inventory.AssetCategoryRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetCategoryRepository categoryRepository;

    @Override
    public AssetDTO createAsset(AssetDTO dto) {
        AssetCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Asset asset = new Asset();
        asset.setName(dto.getName());
        asset.setCategory(category);

        assetRepository.save(asset);
        dto.setId(asset.getId());
        return dto;
    }

    @Override
    public AssetDTO getAsset(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        return mapToDTO(asset);
    }

    @Override
    public List<AssetDTO> getAllAssets() {
        return assetRepository.findByIsActiveTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AssetDTO updateAsset(Long id, AssetDTO dto) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        if (dto.getName() != null) asset.setName(dto.getName());
        if (dto.getCategoryId() != null) {
            AssetCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            asset.setCategory(category);
        }

        assetRepository.save(asset);
        return mapToDTO(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        asset.setIsActive(false);
        assetRepository.save(asset);
    }

    private AssetDTO mapToDTO(Asset asset) {
        AssetDTO dto = new AssetDTO();
        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setCategoryId(asset.getCategory().getId());
        return dto;
    }
}
