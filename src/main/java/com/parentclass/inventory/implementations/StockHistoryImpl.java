package com.parentclass.inventory.implementations;

import com.parentclass.inventory.models.StockHistory;
import com.parentclass.inventory.repositories.StockHistoryRepository;
import com.parentclass.inventory.services.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryImpl implements StockHistoryService {
  @Autowired private StockHistoryRepository stockHistoryRepository;

  @Override
  public Page<StockHistory> getByInventoryId(long inventoryId, int page, int maxResult) {
    return stockHistoryRepository.findByInventoryId(inventoryId, PageRequest.of(page, maxResult));
  }
}
