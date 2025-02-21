package com.zenltd.dto;

import com.zenltd.enums.ShipmentPreparationStatus;

import java.time.LocalDateTime;

public class ShipmentPreparationDto {
    private  long Id;
    private long shipmentId;
    private String gateNumber;
    private LocalDateTime startTime;
    private LocalDateTime lastUpdatedTime;
    private LocalDateTime endTime;
    private ShipmentPreparationStatus status;
    private String remarks; // Any additional comments
    //*******************************************************

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ShipmentPreparationStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentPreparationStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
