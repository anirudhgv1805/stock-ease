package com.anirudhgv.stockease.data.model;

import com.anirudhgv.stockease.data.model.dto.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private UserDto user;
    private LocalDateTime orderData;
    private Status status;
    private BigDecimal totalAmount;
    private List<OrderItem> items;

    public enum Status {
        PENDING, CONFIRMED, SHIPPED, CANCELLED
    }

    public Order() {

    }
    public Order(Long id, UserDto user, LocalDateTime orderData, Status status, BigDecimal totalAmount, List<OrderItem> items) {
        this.id = id;
        this.user = user;
        this.orderData = orderData;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public LocalDateTime getOrderData() {
        return orderData;
    }

    public void setOrderData(LocalDateTime orderData) {
        this.orderData = orderData;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
