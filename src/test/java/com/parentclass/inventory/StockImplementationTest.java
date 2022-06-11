package com.parentclass.inventory;

import com.parentclass.inventory.models.Inventory;
import com.parentclass.inventory.models.Stock;
import com.parentclass.inventory.models.StockHistory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class StockImplementationTest extends InventoryApplicationTests {

  private Inventory persistedInventory;

  @Before
  public void mocks() {
    // pre persist inventory
    Inventory inventory = new Inventory();

    // build up inventory
    inventory.setCode("m#001");
    inventory.setName("mock inventory#1");
    inventory.setDescription("a simple mock inventory#1");

    // persist inventory
    persistedInventory = inventoryRepository.save(inventory);
  }

  @Test
  public void should_createStock() {
    // expected stock
    Stock expectedStock = new Stock();

    // build up stock
    expectedStock.setInventoryId(persistedInventory.getId());
    expectedStock.setAmount(10);

    // actual stock
    Stock actualStock = stock.create(expectedStock);

    // assumes that the persisted stock shares the same object with the expectation
    // except id, since its dynamically created
    Assertions.assertThat(actualStock)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(expectedStock);
  }

  @Test
  public void should_createStockHistoryEveryStockModification() {
    // mocked stock
    Stock mockedStock = new Stock();

    // building up mock stock
    mockedStock.setInventoryId(persistedInventory.getId());
    mockedStock.setAmount(10);

    // persist stock, after this process, a stock history should be recorded
    Stock persistedStock = stock.create(mockedStock);

    // build up updated persisted stock
    persistedStock.setAmount(20);

    // update the persisted stock, which should also record a stock history
    stock.update(persistedStock.getId(), persistedStock);

    // retrieve all stock history, doesn't matter if we retrieve all or by inventory
    // since we only have to check for this specific test run
    List<StockHistory> stockHistoryList = stockHistoryRepository.findAll();

    // assert that there's already 2 record stock history, since we create a stock then modify it
    Assertions.assertThat(stockHistoryList.size()).isEqualTo(2);
  }

  @Test
  public void should_updateStock() {
    // mocked stock
    Stock mockedStock = new Stock();

    // building up mock stock
    mockedStock.setInventoryId(persistedInventory.getId());
    mockedStock.setAmount(10);

    // expected stock
    Stock expectedStock = stock.create(mockedStock);

    // build modified expected stock
    expectedStock.setAmount(5);

    // actual stock
    Stock actualStock = stock.update(expectedStock.getId(), expectedStock);

    // asserts that the actual stock will have the same object with our modified stock
    Assertions.assertThat(actualStock).isEqualTo(expectedStock);
  }

  @Test(expected = ResponseStatusException.class)
  public void should_throwErrorForNonNullAndZeroInventoryId() {
    // mock stock
    Stock mockedStock = new Stock();

    // build up stock
    mockedStock.setInventoryId(0);
    mockedStock.setAmount(10);

    // invoke create that should throw an error, due to inventory id set is 0
    stock.create(mockedStock);
  }

  @Test(expected = ResponseStatusException.class)
  public void should_throwErrorForNonExistentInventoryId() {
    // mock stock
    Stock mockedStock = new Stock();

    // build up stock
    mockedStock.setInventoryId(999);
    mockedStock.setAmount(10);

    // invoke create that should throw an error, due to inventory id set is 0
    stock.create(mockedStock);
  }
}
