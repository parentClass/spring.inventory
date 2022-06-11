package com.parentclass.inventory.controllers;

import com.parentclass.inventory.models.Stock;
import com.parentclass.inventory.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {
  @Autowired private StockService stockService;

  @PostMapping
  public ResponseEntity<Stock> create(@RequestBody Stock stock) {
    return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
  }

  @PutMapping("/{id}/")
  public ResponseEntity<Stock> update(@PathVariable long id, @RequestBody Stock stock) {
    return new ResponseEntity<>(stockService.update(id, stock), HttpStatus.OK);
  }
}
