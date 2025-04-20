package com.anirudhgv.stockease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.model.InventoryLog;
import com.anirudhgv.stockease.model.User;
import com.anirudhgv.stockease.repository.InventoryLogRepository;
import com.anirudhgv.stockease.repository.UserRepository;
import com.anirudhgv.stockease.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Inventory> updateInventoryWithLog(
            @PathVariable Long productId,
            @RequestParam int quantityChange,
            @RequestParam String reason,
            @RequestParam(required = false) Long userId) {
        User user = null;

        if (userId != null) {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        }

        Inventory updatedInventory = inventoryService.updateInventory(productId, quantityChange, reason, user);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long productId) {
        inventoryService.deleteInventory(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{productId}")
    public List<InventoryLog> getInventoryHistory(@PathVariable Long productId) {
        return inventoryLogRepository.findByProductId(productId);
    }

}
