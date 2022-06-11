package com.parentclass.inventory.controllers;

import com.parentclass.inventory.models.StockHistory;
import com.parentclass.inventory.services.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stockhistory")
public class StockHistoryController {
  @Autowired private StockHistoryService stockHistoryService;

  @GetMapping("/{inventoryId}/")
  public ResponseEntity<Page<StockHistory>> getById(
      @PathVariable long inventoryId, @RequestParam int page, @RequestParam int maxResult) {
    return new ResponseEntity<>(
        stockHistoryService.getByInventoryId(inventoryId, page, maxResult), HttpStatus.OK);
  }
}
