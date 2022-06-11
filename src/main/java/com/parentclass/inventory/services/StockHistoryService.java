package com.parentclass.inventory.services;

import com.parentclass.inventory.models.StockHistory;
import org.springframework.data.domain.Page;

public interface StockHistoryService {
    /**
     * Retrieve all stock history associated to the stock
     * @param inventoryId - id of the inventory which stock history is requested
     * @param page - current index of pagination
	 * @param maxResult - max records per page to return
     * @return list of persisted and associated stock history
     */
    public Page<StockHistory> getByInventoryId(long inventoryId, int page, int maxResult);
}
