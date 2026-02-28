package com.My.Spring_Final_Project.Service.inventory;

import com.My.Spring_Final_Project.DTO.inventory.AssetAllocationDTO;
import com.My.Spring_Final_Project.Entity.inventory.Asset;
import com.My.Spring_Final_Project.Entity.inventory.AssetAllocation;
import com.My.Spring_Final_Project.Repository.inventory.AssetAllocationRepository;
import com.My.Spring_Final_Project.Repository.inventory.AssetRepository;
import com.My.Spring_Final_Project.Service.inventory.interfaces.AssetAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetAllocationServiceImpl implements AssetAllocationService {

    private final AssetAllocationRepository allocationRepository;
    private final AssetRepository assetRepository;

    @Override
    public AssetAllocationDTO allocateAsset(AssetAllocationDTO dto) {
        Asset asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        AssetAllocation allocation = new AssetAllocation();
        allocation.setAsset(asset);
        allocation.setAllocatedTo(dto.getAllocatedTo());
        allocation.setAllocatedAt(dto.getAllocatedAt());
        allocationRepository.save(allocation);
        dto.setId(allocation.getId());
        return dto;
    }

    @Override
    public AssetAllocationDTO getAllocation(Long id) {
        AssetAllocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
        return mapToDTO(allocation);
    }

    @Override
    public List<AssetAllocationDTO> getAllocationsByAsset(Long assetId) {
        return allocationRepository.findByAssetId(assetId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllocation(Long id) {
        AssetAllocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
        allocation.setIsActive(false);
        allocationRepository.save(allocation);
    }

    private AssetAllocationDTO mapToDTO(AssetAllocation allocation) {
        AssetAllocationDTO dto = new AssetAllocationDTO();
        dto.setId(allocation.getId());
        dto.setAssetId(allocation.getAsset().getId());
        dto.setAllocatedTo(allocation.getAllocatedTo());
        dto.setAllocatedAt(allocation.getAllocatedAt());
        return dto;
    }
}
