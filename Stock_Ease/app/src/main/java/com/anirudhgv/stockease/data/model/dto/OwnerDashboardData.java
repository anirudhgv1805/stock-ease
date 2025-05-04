package com.anirudhgv.stockease.data.model.dto;

import androidx.annotation.NonNull;

import java.util.Objects;

public class OwnerDashboardData {

    private Long pendingOrders;  
    private Long processingOrders;
    private Long completedOrders;

    public OwnerDashboardData() {
    }

    public OwnerDashboardData(Long pendingOrders, Long processingOrders, Long completedOrders) {
        this.pendingOrders = pendingOrders;
        this.processingOrders = processingOrders;
        this.completedOrders = completedOrders;
    }

    public Long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public Long getProcessingOrders() {
        return processingOrders;
    }

    public void setProcessingOrders(Long processingOrders) {
        this.processingOrders = processingOrders;
    }

    public Long getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Long completedOrders) {
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