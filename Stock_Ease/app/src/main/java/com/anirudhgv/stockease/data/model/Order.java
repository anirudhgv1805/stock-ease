package com.anirudhgv.stockease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.anirudhgv.stockease.data.model.dto.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Parcelable {
    private Long orderId;
    private UserDto user;
    private LocalDateTime orderDate;
    private Status status;
    private BigDecimal totalAmount;
    private List<OrderItem> items;

    public Order() {

    }

    public Order(Parcel in) {
        if (in.readByte() == 0) {
            orderId = null;
        } else {
            orderId = in.readLong();
        }
        user = in.readParcelable(UserDto.class.getClassLoader());
        orderDate = (LocalDateTime) in.readSerializable();
        status = Status.values()[in.readInt()]; // Read the ordinal value of Status
        totalAmount = (BigDecimal) in.readSerializable();
        items = in.createTypedArrayList(OrderItem.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (orderId == null) {
            dest.writeByte((byte) 0);  // Write a flag to indicate that `id` is null
        } else {
            dest.writeByte((byte) 1);  // Write a flag to indicate that `id` is not null
            dest.writeLong(orderId);
        }

        dest.writeParcelable(user, flags);  // Write `user` object
        dest.writeSerializable(orderDate);  // Write `orderDate` as `LocalDateTime`
        dest.writeInt(status.ordinal());  // Write `status` enum as an integer (ordinal)
        dest.writeSerializable(totalAmount);  // Write `totalAmount` as `BigDecimal`
        dest.writeTypedList(items);  // Write list of `OrderItem` objects
    }

    public enum Status {
        PENDING, CONFIRMED, PROCESSED, SHIPPED, DELIVERED, CANCELLED
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setId(Long orderId) { this.orderId = orderId; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    @NonNull
    @Override
    public String toString() {
        return "Order{" +
                "id=" + orderId +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", items=" + items +
                '}';
    }
}
