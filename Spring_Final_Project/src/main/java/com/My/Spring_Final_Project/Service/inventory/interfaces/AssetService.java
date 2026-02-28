package com.My.Spring_Final_Project.Service.inventory.interfaces;

import com.My.Spring_Final_Project.DTO.inventory.AssetDTO;
import java.util.List;

public interface AssetService {
    AssetDTO createAsset(AssetDTO dto);
    AssetDTO getAsset(Long id);
    List<AssetDTO> getAllAssets();
    AssetDTO updateAsset(Long id, AssetDTO dto);
    void deleteAsset(Long id); // soft delete
}
