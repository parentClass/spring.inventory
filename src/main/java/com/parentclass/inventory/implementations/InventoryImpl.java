package com.parentclass.inventory.implementations;

import com.parentclass.inventory.models.Inventory;
import com.parentclass.inventory.repositories.InventoryRepository;
import com.parentclass.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InventoryImpl implements InventoryService {

  @Autowired private InventoryRepository inventoryRepository;

  @Override
  public Inventory create(Inventory inventory) {
    return inventoryRepository.save(inventory);
  }

  @Override
  public Inventory update(long id, Inventory inventory) {
    Inventory existingInventory =
        inventoryRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Inventory with id: " + id + " is not existing."));

    // Set existing inventory to new update inventory, only update those supplied in the request
    if (inventory.getCode() != null) existingInventory.setCode(inventory.getCode());
    if (inventory.getName() != null) existingInventory.setName(inventory.getName());
    if (inventory.getDescription() != null)
      existingInventory.setDescription(inventory.getDescription());

    return inventoryRepository.save(existingInventory);
  }

  @Override
  public Page<Inventory> getAll(int page, int maxResult) {
    return inventoryRepository.findAll(PageRequest.of(page, maxResult));
  }
}
