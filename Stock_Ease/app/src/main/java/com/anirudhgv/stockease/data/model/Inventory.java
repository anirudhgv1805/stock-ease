package com.anirudhgv.stockease.data.model;

import java.time.LocalDateTime;

public class Inventory {
    private Long inventoryId;
    private Product product;
    private Integer quantity;
    private LocalDateTime lastUpdated;

    public Inventory() {
    }

    public Inventory(Long inventoryId, Product product, Integer quantity, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
