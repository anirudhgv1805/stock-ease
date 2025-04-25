package com.anirudhgv.stockease.model.response;
import java.util.Objects;

public class OwnerDashboardData {

    private int salesCount;
    private int orderCount;

    public OwnerDashboardData() {
    }

    public OwnerDashboardData(int salesCount, int orderCount) {
        this.salesCount = salesCount;
        this.orderCount = orderCount;
    }

    public int getSalesCount() {
        return this.salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public OwnerDashboardData salesCount(int salesCount) {
        setSalesCount(salesCount);
        return this;
    }

    public OwnerDashboardData orderCount(int orderCount) {
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

    @Override
    public String toString() {
        return "{" +
            " salesCount='" + getSalesCount() + "'" +
            ", orderCount='" + getOrderCount() + "'" +
            "}";
    }

}
