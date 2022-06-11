package com.parentclass.inventory;

import com.parentclass.inventory.models.Inventory;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import java.util.Arrays;
import java.util.List;

public class InventoryImplementationTest extends InventoryApplicationTests {
  @Test
  public void should_createInventory() {
    // expected inventory
    Inventory expectedInventory = new Inventory();

    // building up expected inventory
    expectedInventory.setCode("m#111");
    expectedInventory.setName("mock inventory");
    expectedInventory.setDescription("a simple mock inventory");

    // actual inventory
    Inventory actualInventory = inventory.create(expectedInventory);

    // assumes that the persisted inventory shares the same object with the expectation
    // except id, since its dynamically created
    Assertions.assertThat(actualInventory)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(expectedInventory);
  }

  @Test
  public void should_updateInventory() {
    // mocked inventory
    Inventory mockedInventory = new Inventory();

    // building up mocked inventory
    mockedInventory.setCode("m#111");
    mockedInventory.setName("mock inventory");
    mockedInventory.setDescription("a simple mock inventory");

    // expected inventory
    Inventory expectedInventory = inventoryRepository.save(mockedInventory);

    // build modified expected inventory
    expectedInventory.setCode("m#001");
    expectedInventory.setName("modified inventory");
    expectedInventory.setDescription("still a simple mock, but modified");

    // actual inventory
    Inventory actualInventory = inventory.update(expectedInventory.getId(), expectedInventory);

    // asserts that the actual inventory will have the same object with our modified inventory
    Assertions.assertThat(actualInventory).isEqualTo(expectedInventory);
  }

  @Test
  public void should_getAllInventory() {
    // mock 3 inventory
    Inventory inventory1 = new Inventory();
    Inventory inventory2 = new Inventory();
    Inventory inventory3 = new Inventory();

    inventory1.setCode("m#001");
    inventory1.setName("mock inventory#1");
    inventory1.setDescription("a simple mock inventory#1");

    inventory2.setCode("m#002");
    inventory2.setName("mock inventory#2");
    inventory2.setDescription("a simple mock inventory#2");

    inventory3.setCode("m#003");
    inventory3.setName("mock inventory#3");
    inventory3.setDescription("a simple mock inventory#3");

    // collate inventory to a list for batch save
    List<Inventory> inventoryList = Arrays.asList(inventory1, inventory2, inventory3);

    // pre persist and expected inventory list
    List<Inventory> expectedInventoryList = inventoryRepository.saveAll(inventoryList);

    // actual inventory list
    List<Inventory> actualInventoryList = inventory.getAll(0, 3).stream().toList();

    // assumes that the size are equal
    Assertions.assertThat(actualInventoryList.size()).isEqualTo(expectedInventoryList.size());
    // assumes that the retrieved list of inventory are also equal with our pre persisted inventory
    // list
    Assertions.assertThat(actualInventoryList).isEqualTo(expectedInventoryList);
  }
}
