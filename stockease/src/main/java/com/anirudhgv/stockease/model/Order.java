package com.anirudhgv.stockease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String item;
    private int quantity;
    private String status;
    private String processedAt;
    private double price;
    private String clientName;

    public Order() {}

    public Order(String item, int quantity, String clientName) {
        this.item = item;
        this.quantity = quantity;
        this.clientName = clientName;
        this.status = "pending";
    }

    public Order(int id, String item, int quantity, String status, String processedAt, double price, String clientName) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
        this.processedAt = processedAt;
        this.price = price;
        this.clientName = clientName;
    }

    public int getId() { return id; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public String getProcessedAt() { return processedAt; }
    public double getPrice() { return price; }
    public String getClientName() { return clientName; }

    public void setId(int id) { this.id = id; }
    public void setItem(String item) { this.item = item; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setStatus(String status) { this.status = status; }
    public void setProcessedAt(String processedAt) { this.processedAt = processedAt; }
    public void setPrice(double price) { this.price = price; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", item='" + getItem() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", status='" + getStatus() + "'" +
            ", processedAt='" + getProcessedAt() + "'" +
            ", price='" + getPrice() + "'" +
            ", clientName='" + getClientName() + "'" +
            "}";
    }

    
}
