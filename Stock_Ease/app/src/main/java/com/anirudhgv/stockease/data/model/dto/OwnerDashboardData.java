package com.anirudhgv.stockease.data.model.dto;

import androidx.annotation.NonNull;

import java.util.Objects;

public class OwnerDashboardData {

    private Number salesCount;
    private Number orderCount;

    public OwnerDashboardData() {
    }

    public OwnerDashboardData(Number salesCount, Number orderCount) {
        this.salesCount = salesCount;
        this.orderCount = orderCount;
    }

    public Number getSalesCount() {
        return this.salesCount;
    }

    public void setSalesCount(Number salesCount) {
        this.salesCount = salesCount;
    }

    public Number getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(Number orderCount) {
        this.orderCount = orderCount;
    }

    public OwnerDashboardData salesCount(Number salesCount) {
        setSalesCount(salesCount);
        return this;
    }

    public OwnerDashboardData orderCount(Number orderCount) {
        setOrderCount(orderCount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OwnerDashboardData)) {
            return false;
        }
        OwnerDashboardData ownerDashboardData = (OwnerDashboardData) o;
        return Objects.equals(salesCount, ownerDashboardData.salesCount) && Objects.equals(orderCount, ownerDashboardData.orderCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesCount, orderCount);
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                " salesCount='" + getSalesCount() + "'" +
                ", orderCount='" + getOrderCount() + "'" +
                "}";
    }

}
