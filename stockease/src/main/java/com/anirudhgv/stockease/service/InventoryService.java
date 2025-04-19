package com.anirudhgv.stockease.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
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

    public void deleteInventory(Long productId) {
        inventoryRepository.deleteById(productId);
    }
}
