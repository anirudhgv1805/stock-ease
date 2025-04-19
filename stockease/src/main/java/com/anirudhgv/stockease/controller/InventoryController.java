package com.anirudhgv.stockease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable Long productId, @RequestParam int quantityChange) {
        return inventoryService.updateInventory(productId, quantityChange);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long productId) {
        inventoryService.deleteInventory(productId);
        return ResponseEntity.ok().build();
    }
}

