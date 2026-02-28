package com.My.Spring_Final_Project.Repository.inventory;

import com.My.Spring_Final_Project.Entity.inventory.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    List<StockItem> findByIsActiveTrue();
    Long countByIsActiveTrue();

    @Query("SELECT COUNT(s) FROM StockItem s WHERE s.quantity < 5") // example threshold
    Long countLowStockItems();

}
