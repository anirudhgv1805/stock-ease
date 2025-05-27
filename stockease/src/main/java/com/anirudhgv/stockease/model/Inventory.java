package com.anirudhgv.stockease.model;


import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private LocalDateTime lastUpdated = LocalDateTime.now();


    public Inventory() {
    }

    public Inventory(Long inventoryId, Product product, Integer quantity, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public Long getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Inventory inventoryId(Long inventoryId) {
        setInventoryId(inventoryId);
        return this;
    }

    public Inventory product(Product product) {
        setProduct(product);
        return this;
    }

    public Inventory quantity(Integer quantity) {
        setQuantity(quantity);
        return this;
    }

    public Inventory lastUpdated(LocalDateTime lastUpdated) {
        setLastUpdated(lastUpdated);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Inventory)) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.inventoryId) && Objects.equals(product, inventory.product) && Objects.equals(quantity, inventory.quantity) && Objects.equals(lastUpdated, inventory.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, product, quantity, lastUpdated);
    }

    @Override
    public String toString() {
        return "{" +
            " inventoryId='" + getInventoryId() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            "}";
    }
    
}
