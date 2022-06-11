package com.parentclass.inventory.implementations;

import com.parentclass.inventory.models.Stock;
import com.parentclass.inventory.models.StockHistory;
import com.parentclass.inventory.repositories.InventoryRepository;
import com.parentclass.inventory.repositories.StockHistoryRepository;
import com.parentclass.inventory.repositories.StockRepository;
import com.parentclass.inventory.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@Service
public class StockImpl implements StockService {
  @Autowired private StockRepository stockRepository;

  @Autowired private StockHistoryRepository stockHistoryRepository;

  @Autowired private InventoryRepository inventoryRepository;

  /**
   * Persist a stock record
   *
   * @param stock - stock object to be persisted
   * @return Stock
   */
  @Override
  public Stock create(Stock stock) {
    int oldAmount = 0;
    long stockId = 0;
    long inventoryId = 0;
    Stock newStock = null;

    // Retrieve existing stock amount if exist
    Optional<Stock> existingStock = stockRepository.findById(stock.getId());

    // Only set old amount if there's an existing record, otherwise it signifies stock amount is new
    // entry, set to 0
    if (existingStock.isPresent()) {
      stockId = existingStock.get().getId();
      oldAmount = existingStock.get().getAmount();
    } else {
      // validations
      validateNonNullAndZeroInventoryId(stock.getInventoryId());
      validateInventoryId(stock.getInventoryId());

      // new stock should persist first before history, so we will have access to new persisted
      // stock's id
      newStock = stockRepository.save(stock);
      stockId = newStock.getId();
      inventoryId = newStock.getInventoryId();
    }

    // Persist a record about this new stock
    persistStockModification(stockId, inventoryId, oldAmount, stock.getAmount());

    return newStock;
  }

  /**
   * Stock to modify its existing record
   *
   * @param id - id of the stock
   * @param stock - stock object to be used as the basis of the updated record
   * @return Stock
   */
  @Override
  public Stock update(long id, Stock stock) {
    Stock existingStock =
        stockRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Stock with id: " + id + " is not existing."));

    // validations
    validateNonNullAndZeroInventoryId(stock.getInventoryId());
    validateInventoryId(stock.getInventoryId());

    // Persist a record about this new stock
    persistStockModification(
        existingStock.getId(),
        existingStock.getInventoryId(),
        existingStock.getAmount(),
        stock.getAmount());

    // Set existing stock to new update stock
    existingStock.setAmount(stock.getAmount());

    return stockRepository.save(existingStock);
  }

  /**
   * Process that will persist an entry in stock history, normally done whenever a stock
   * modification takes place
   *
   * @param stockId - id of the stock the history is associated
   * @param inventoryId - id of the inventory the history is associated
   * @param oldAmount - existing amount of stock
   * @param newAmount - new amount of stock
   */
  private void persistStockModification(
      long stockId, long inventoryId, int oldAmount, int newAmount) {
    StockHistory stockHistory = new StockHistory();

    stockHistory.setInventoryId(inventoryId);
    stockHistory.setStockId(stockId);
    stockHistory.setOldAmount(oldAmount);
    stockHistory.setNewAmount(newAmount);

    try {
      // Every insertion of stock, should persist a history
      stockHistoryRepository.save(stockHistory);
    } catch (Exception ex) {
      // Catch any possible jpa problem
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "There was a problem on persisting stock history");
    }
  }

  /**
   * Validates inventory id that is being associated to the stock
   *
   * @param inventoryId - id of the inventory that are being associated to the request stock
   */
  private void validateInventoryId(long inventoryId) {
    // validate that the inventory id correlating to is existing
    if (!inventoryRepository.existsById(inventoryId)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Inventory correlating to is non existent");
    }
  }

  /**
   * Validates inventory id for null and zero values
   *
   * @param inventoryId - id of the inventory that are being associated to the request stock
   */
  private void validateNonNullAndZeroInventoryId(long inventoryId) {
    // validate that the inventory id is non-null and non-zero value
    if (inventoryId == 0) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Inventory id should be non-zero value or null");
    }
  }
}
