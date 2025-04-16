package com.anirudhgv.stockease.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int quantity;
    private double pricePerUnit;
    private double totalPrice;


    public OrderItem() {
    }

    public OrderItem(Long id, Order order, Item item, int quantity, double pricePerUnit, double totalPrice) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderItem id(Long id) {
        setId(id);
        return this;
    }

    public OrderItem order(Order order) {
        setOrder(order);
        return this;
    }

    public OrderItem item(Item item) {
        setItem(item);
        return this;
    }

    public OrderItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public OrderItem pricePerUnit(double pricePerUnit) {
        setPricePerUnit(pricePerUnit);
        return this;
    }

    public OrderItem totalPrice(double totalPrice) {
        setTotalPrice(totalPrice);
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
        return Objects.equals(id, orderItem.id) && Objects.equals(order, orderItem.order) && Objects.equals(item, orderItem.item) && quantity == orderItem.quantity && pricePerUnit == orderItem.pricePerUnit && totalPrice == orderItem.totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, item, quantity, pricePerUnit, totalPrice);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", order='" + getOrder() + "'" +
            ", item='" + getItem() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", pricePerUnit='" + getPricePerUnit() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            "}";
    }
    
}

