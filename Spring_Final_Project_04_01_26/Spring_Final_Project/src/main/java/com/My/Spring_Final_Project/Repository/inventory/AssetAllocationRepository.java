package com.My.Spring_Final_Project.Repository.inventory;

import com.My.Spring_Final_Project.Entity.inventory.AssetAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation, Long> {
    List<AssetAllocation> findByAssetId(Long assetId);
}
