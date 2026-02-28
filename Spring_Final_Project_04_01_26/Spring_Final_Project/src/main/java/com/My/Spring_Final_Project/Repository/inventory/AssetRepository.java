package com.My.Spring_Final_Project.Repository.inventory;

import com.My.Spring_Final_Project.Entity.inventory.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByIsActiveTrue();

    Long countByIsActiveTrue();

    @Query("SELECT COUNT(a) FROM Asset a WHERE a.id IN (SELECT DISTINCT al.asset.id FROM AssetAllocation al WHERE al.isActive = true)")
    Long countAllocatedAssets();


}
