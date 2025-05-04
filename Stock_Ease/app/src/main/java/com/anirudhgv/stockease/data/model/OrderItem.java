package com.anirudhgv.stockease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class OrderItem implements Parcelable {
    private Long orderItemId;
    private Order order;
    private Product product;
    private Integer quantity;
    private BigDecimal priceAtOrder;

    public OrderItem() { }

    public OrderItem(Long orderItemId, Order order, Product product, Integer quantity, BigDecimal priceAtOrder) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    protected OrderItem(Parcel in) {
        if (in.readByte() == 0) {
            orderItemId = null;
        } else {
            orderItemId = in.readLong();
        }
        order = in.readParcelable(Order.class.getClassLoader());
        product = in.readParcelable(Product.class.getClassLoader());
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        priceAtOrder = (BigDecimal) in.readSerializable();
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (orderItemId == null) {
            dest.writeByte((byte) 0);  // Write flag for null `orderItemId`
        } else {
            dest.writeByte((byte) 1);  // Write flag for non-null `orderItemId`
            dest.writeLong(orderItemId);
        }

        dest.writeParcelable(order, flags);  // Write `order` object
        dest.writeParcelable(product, flags);  // Write `product` object
        if (quantity == null) {
            dest.writeByte((byte) 0);  // Write flag for null `quantity`
        } else {
            dest.writeByte((byte) 1);  // Write flag for non-null `quantity`
            dest.writeInt(quantity);
        }

        dest.writeSerializable(priceAtOrder);  // Write `priceAtOrder` as `BigDecimal`
    }

    // Getters and Setters
    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(BigDecimal priceAtOrder) { this.priceAtOrder = priceAtOrder; }

    @NonNull
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", priceAtOrder=" + priceAtOrder +
                '}';
    }
}
