package com.parentclass.inventory;

import com.parentclass.inventory.implementations.InventoryImpl;
import com.parentclass.inventory.implementations.StockHistoryImpl;
import com.parentclass.inventory.implementations.StockImpl;
import com.parentclass.inventory.repositories.InventoryRepository;
import com.parentclass.inventory.repositories.StockHistoryRepository;
import com.parentclass.inventory.repositories.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public abstract class InventoryApplicationTests {
  @Autowired protected InventoryImpl inventory;
  @Autowired protected StockImpl stock;
  @Autowired protected StockHistoryImpl stockHistory;

  @Autowired protected InventoryRepository inventoryRepository;
  @Autowired protected StockRepository stockRepository;
  @Autowired protected StockHistoryRepository stockHistoryRepository;

  @Test
  void contextLoads() {}

  @After
  public void cleanup() {
    inventoryRepository.deleteAll();
    stockRepository.deleteAll();
    stockHistoryRepository.deleteAll();
  }
}
