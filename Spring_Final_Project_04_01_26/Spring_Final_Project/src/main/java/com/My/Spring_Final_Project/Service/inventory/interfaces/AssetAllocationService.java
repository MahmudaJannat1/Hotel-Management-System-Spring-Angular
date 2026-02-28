package com.My.Spring_Final_Project.Service.inventory.interfaces;

import com.My.Spring_Final_Project.DTO.inventory.AssetAllocationDTO;
import java.util.List;

public interface AssetAllocationService {
    AssetAllocationDTO allocateAsset(AssetAllocationDTO dto);
    AssetAllocationDTO getAllocation(Long id);
    List<AssetAllocationDTO> getAllocationsByAsset(Long assetId);
    void deleteAllocation(Long id); // soft delete
}
