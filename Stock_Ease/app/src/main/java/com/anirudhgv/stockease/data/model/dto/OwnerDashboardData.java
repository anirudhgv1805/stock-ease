package com.anirudhgv.stockease.data.model.dto;

import androidx.annotation.NonNull;

import java.util.Objects;

public class OwnerDashboardData {

    private int pendingOrders;
    private int processingOrders;
    private int completedOrders;

    public OwnerDashboardData() {
    }

    public OwnerDashboardData(int pendingOrders, int processingOrders, int completedOrders) {
        this.pendingOrders = pendingOrders;
        this.processingOrders = processingOrders;
        this.completedOrders = completedOrders;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public int getProcessingOrders() {
        return processingOrders;
    }

    public void setProcessingOrders(int processingOrders) {
        this.processingOrders = processingOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    @NonNull
    @Override
    public String toString() {
        return "OwnerDashboardData{" +
                "pendingOrders=" + pendingOrders +
                ", processingOrders=" + processingOrders +
                ", completedOrders=" + completedOrders +
                '}';
    }
}