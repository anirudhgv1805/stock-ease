package com.anirudhgv.stockease.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; 

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    private Double totalAmount;

    @ManyToOne
    private Client client;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    public Order() {
    }

    public Order(Long id, String status, LocalDateTime createdAt, LocalDateTime completedAt, Double totalAmount, Client client, User createdBy, List<OrderItem> orderItems) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.totalAmount = totalAmount;
        this.client = client;
        this.createdBy = createdBy;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return this.completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order id(Long id) {
        setId(id);
        return this;
    }

    public Order status(String status) {
        setStatus(status);
        return this;
    }

    public Order createdAt(LocalDateTime createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Order completedAt(LocalDateTime completedAt) {
        setCompletedAt(completedAt);
        return this;
    }

    public Order totalAmount(Double totalAmount) {
        setTotalAmount(totalAmount);
        return this;
    }

    public Order client(Client client) {
        setClient(client);
        return this;
    }

    public Order createdBy(User createdBy) {
        setCreatedBy(createdBy);
        return this;
    }

    public Order orderItems(List<OrderItem> orderItems) {
        setOrderItems(orderItems);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(status, order.status) && Objects.equals(createdAt, order.createdAt) && Objects.equals(completedAt, order.completedAt) && Objects.equals(totalAmount, order.totalAmount) && Objects.equals(client, order.client) && Objects.equals(createdBy, order.createdBy) && Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, completedAt, totalAmount, client, createdBy, orderItems);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", completedAt='" + getCompletedAt() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", client='" + getClient() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", orderItems='" + getOrderItems() + "'" +
            "}";
    }
    
}

