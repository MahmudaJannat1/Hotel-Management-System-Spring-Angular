package com.My.Spring_Final_Project.Repository.inventory;

import com.My.Spring_Final_Project.Entity.inventory.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {
    List<AssetCategory> findByIsActiveTrue();
//    Optional<AssetCategory> findById(Long id);


}
