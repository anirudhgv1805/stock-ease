package com.anirudhgv.stockease.model;


import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private BigDecimal priceAtOrder;


    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Order order, Product product, Integer quantity, BigDecimal priceAtOrder) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public Long getOrderItemId() {
        return this.orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public BigDecimal getPriceAtOrder() {
        return this.priceAtOrder;
    }

    public void setPriceAtOrder(BigDecimal priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }

    public OrderItem orderItemId(Long orderItemId) {
        setOrderItemId(orderItemId);
        return this;
    }

    public OrderItem order(Order order) {
        setOrder(order);
        return this;
    }

    public OrderItem product(Product product) {
        setProduct(product);
        return this;
    }

    public OrderItem quantity(Integer quantity) {
        setQuantity(quantity);
        return this;
    }

    public OrderItem priceAtOrder(BigDecimal priceAtOrder) {
        setPriceAtOrder(priceAtOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItem)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(orderItemId, orderItem.orderItemId) && Objects.equals(order, orderItem.order) && Objects.equals(product, orderItem.product) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(priceAtOrder, orderItem.priceAtOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, order, product, quantity, priceAtOrder);
    }

    @Override
    public String toString() {
        return "{" +
            " orderItemId='" + getOrderItemId() + "'" +
            ", order='" + getOrder() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", priceAtOrder='" + getPriceAtOrder() + "'" +
            "}";
    }
    
}

