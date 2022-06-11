package com.parentclass.inventory.controllers;

import com.parentclass.inventory.models.Inventory;
import com.parentclass.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
  @Autowired private InventoryService inventoryService;

  @PostMapping
  public ResponseEntity<Inventory> create(@RequestBody Inventory inventory) {
    return new ResponseEntity<>(inventoryService.create(inventory), HttpStatus.CREATED);
  }

  @GetMapping("/all/")
  public ResponseEntity<Page<Inventory>> getAll(
      @RequestParam int page, @RequestParam int maxResult) {
    return new ResponseEntity<>(inventoryService.getAll(page, maxResult), HttpStatus.OK);
  }

  @PutMapping("/{id}/")
  public ResponseEntity<Inventory> update(@PathVariable long id, @RequestBody Inventory inventory) {
    return new ResponseEntity<>(inventoryService.update(id, inventory), HttpStatus.OK);
  }
}
