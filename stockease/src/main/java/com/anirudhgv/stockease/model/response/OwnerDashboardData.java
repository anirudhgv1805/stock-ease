package com.anirudhgv.stockease.model.response;

public class OwnerDashboardData {

    private Long pendingOrders;
    private Long processingOrders;
    private Long completedOrders;

    public OwnerDashboardData() {
    }

    public OwnerDashboardData(Long[] counts){
        this.pendingOrders = counts[0];
        this.processingOrders = counts[1];
        this.completedOrders = counts[2];
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

    @Override
    public String toString() {
        return "OwnerDashboardData{" +
                "pendingOrders=" + pendingOrders +
                ", processingOrders=" + processingOrders +
                ", completedOrders=" + completedOrders +
                '}';
    }
}