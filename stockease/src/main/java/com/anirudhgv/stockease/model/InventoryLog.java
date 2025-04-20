package com.anirudhgv.stockease.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InventoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int changeAmount;

    private int newQuantity;

    private String reason;

    private LocalDateTime timestamp;

    @ManyToOne
    private User user;


    public InventoryLog() {
    }

    public InventoryLog(Long id, Product product, int changeAmount, int newQuantity, String reason, LocalDateTime timestamp, User user) {
        this.id = id;
        this.product = product;
        this.changeAmount = changeAmount;
        this.newQuantity = newQuantity;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getChangeAmount() {
        return this.changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }

    public int getNewQuantity() {
        return this.newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InventoryLog id(Long id) {
        setId(id);
        return this;
    }

    public InventoryLog product(Product product) {
        setProduct(product);
        return this;
    }

    public InventoryLog changeAmount(int changeAmount) {
        setChangeAmount(changeAmount);
        return this;
    }

    public InventoryLog newQuantity(int newQuantity) {
        setNewQuantity(newQuantity);
        return this;
    }

    public InventoryLog reason(String reason) {
        setReason(reason);
        return this;
    }

    public InventoryLog timestamp(LocalDateTime timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    public InventoryLog user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InventoryLog)) {
            return false;
        }
        InventoryLog inventoryLog = (InventoryLog) o;
        return Objects.equals(id, inventoryLog.id) && Objects.equals(product, inventoryLog.product) && changeAmount == inventoryLog.changeAmount && newQuantity == inventoryLog.newQuantity && Objects.equals(reason, inventoryLog.reason) && Objects.equals(timestamp, inventoryLog.timestamp) && Objects.equals(user, inventoryLog.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, changeAmount, newQuantity, reason, timestamp, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", product='" + getProduct() + "'" +
            ", changeAmount='" + getChangeAmount() + "'" +
            ", newQuantity='" + getNewQuantity() + "'" +
            ", reason='" + getReason() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
    
}
