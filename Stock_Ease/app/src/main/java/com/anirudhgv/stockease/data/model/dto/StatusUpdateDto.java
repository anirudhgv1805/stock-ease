package com.anirudhgv.stockease.data.model.dto;

public class StatusUpdateDto {
    private String status;

    public StatusUpdateDto() {}

    public StatusUpdateDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
