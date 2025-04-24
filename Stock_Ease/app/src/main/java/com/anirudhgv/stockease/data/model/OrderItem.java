package com.anirudhgv.stockease.data.model;

import java.math.BigDecimal;

public class OrderItem {
    private Long orderItemId;
    private Order order;
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
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public BigDecimal getPriceAtOrder() {
        return priceAtOrder;
    }

    public void setPriceAtOrder(BigDecimal priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }
}
