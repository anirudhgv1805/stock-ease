package com.anirudhgv.stockease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Inventory implements Parcelable {
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

    protected Inventory(Parcel in) {
        if (in.readByte() == 0) {
            inventoryId = null;
        } else {
            inventoryId = in.readLong();
        }
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
    }

    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (inventoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(inventoryId);
        }
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
    }
}
