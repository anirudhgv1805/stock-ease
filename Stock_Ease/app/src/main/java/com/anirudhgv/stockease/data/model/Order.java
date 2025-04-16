package com.anirudhgv.stockease.data.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Long id;
    private String item;
    private int quantity;
    private String status;
    private Date processedAt;
    private double price;
    private String clientName;

    public Order() {}

    public Order(String item, int quantity, String clientName) {
        this.item = item;
        this.quantity = quantity;
        this.clientName = clientName;
        this.status = "pending";
    }

    public Order(Long id, String item, int quantity, String status, Date processedAt, double price, String clientName) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
        this.processedAt = processedAt;
        this.price = price;
        this.clientName = clientName;
    }

    public Long getId() { return id; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public Date getProcessedAt() { return processedAt; }
    public double getPrice() { return price; }
    public String getClientName() { return clientName; }

    public void setId(Long id) { this.id = id; }
    public void setItem(String item) { this.item = item; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setStatus(String status) { this.status = status; }
    public void setProcessedAt(Date processedAt) { this.processedAt = processedAt; }
    public void setPrice(double price) { this.price = price; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public Date getDateTime() { return this.processedAt;
    }
}
