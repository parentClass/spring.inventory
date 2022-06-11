package com.parentclass.inventory.repositories;

import com.parentclass.inventory.models.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

	@Query("SELECT sh FROM stockhistories sh WHERE inventory_id = :inventoryId ORDER BY created_at DESC")
	public Page<StockHistory> findByInventoryId(long inventoryId, Pageable pageable);
}
