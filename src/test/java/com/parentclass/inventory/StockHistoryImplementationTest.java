package com.parentclass.inventory;

import com.parentclass.inventory.models.Inventory;
import com.parentclass.inventory.models.Stock;
import com.parentclass.inventory.models.StockHistory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class StockHistoryImplementationTest extends InventoryApplicationTests {

  private Inventory persistedInventory;
  private Stock persistedStock;

  @Before
  public void mocks() {
    // mock an inventory
    Inventory expectedInventory = new Inventory();

    // building up expected inventory
    expectedInventory.setCode("m#111");
    expectedInventory.setName("mock inventory");
    expectedInventory.setDescription("a simple mock inventory");

    // persist inventory
    persistedInventory = inventory.create(expectedInventory);

    // mock a stock
    Stock expectedStock = new Stock();

    // build up stock
    expectedStock.setInventoryId(persistedInventory.getId());
    expectedStock.setAmount(10);

    // actual stock
    persistedStock = stock.create(expectedStock);
  }

  @Test
  public void should_retrieveStockHistoryByInventoryId() {
    // expected stock history
    List<StockHistory> expectedStockHistory = new ArrayList<>();

    // mock a single stock history for the stock creation
    StockHistory mockedStockHistory = new StockHistory();

    // build up a single stock history
    mockedStockHistory.setInventoryId(persistedInventory.getId());
    mockedStockHistory.setStockId(persistedStock.getId());
    mockedStockHistory.setOldAmount(0);
    mockedStockHistory.setNewAmount(persistedStock.getAmount());

    // collate mocked stock history to expected stock history list
    expectedStockHistory.add(mockedStockHistory);

    // actual stock history
    List<StockHistory> actualStockHistoryList =
        stockHistory.getByInventoryId(persistedInventory.getId(), 0, 5).stream().toList();

    // asserts that the actual stock history will contain 1 record, due to a previous stock creation
    Assertions.assertThat(actualStockHistoryList.size()).isOne();
  }
}
