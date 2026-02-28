package com.My.Spring_Final_Project.Repository.inventory;

import com.My.Spring_Final_Project.Entity.inventory.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    List<StockTransaction> findByStockItemId(Long stockItemId);
    Long countByIsActiveTrue();

}
