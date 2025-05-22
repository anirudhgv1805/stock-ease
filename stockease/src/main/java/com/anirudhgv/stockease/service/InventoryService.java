package com.anirudhgv.stockease.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.model.InventoryLog;
import com.anirudhgv.stockease.model.User;
import com.anirudhgv.stockease.repository.InventoryLogRepository;
import com.anirudhgv.stockease.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory createInventory(Inventory inventory) {
        Inventory updatedInventory = inventoryRepository.save(inventory);

        InventoryLog log = new InventoryLog();
        log.setProduct(inventory.getProduct());
        log.setChangeAmount(0);
        log.setNewQuantity(0);
        log.setReason("PRODUCT CREATED");
        log.setTimestamp(LocalDateTime.now());
        inventoryLogRepository.save(log);

        return updatedInventory;
    }

    public Inventory getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for product id " + productId));
    }

    public Inventory updateInventory(Long productId, int quantityChange) {
        Inventory inventory = getInventoryByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() + quantityChange);
        return inventoryRepository.save(inventory);
    }

    
    public Inventory updateInventory(Long productId, int quantityChange, String reason, User user) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product ID: " + productId));

        int newQuantity = inventory.getQuantity() + quantityChange;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Inventory quantity cannot go below zero");
        }

        inventory.setQuantity(newQuantity);
        Inventory updatedInventory = inventoryRepository.save(inventory);

        InventoryLog log = new InventoryLog();
        log.setProduct(inventory.getProduct());
        log.setChangeAmount(quantityChange);
        log.setNewQuantity(newQuantity);
        log.setReason(reason);
        log.setTimestamp(LocalDateTime.now());

        if (user != null) {
            log.setUser(user);
        }

        inventoryLogRepository.save(log);

        return updatedInventory;
    }

    public void deleteInventory(Long productId) {
        inventoryRepository.deleteById(productId);
    }
    
}
