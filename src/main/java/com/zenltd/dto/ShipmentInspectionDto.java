package com.zenltd.dto;

import com.zenltd.enums.ShipmentInspectionStatus;

import java.time.LocalDateTime;

public class ShipmentInspectionDto {
    private long id;
    private long shipmentId;
    private ShipmentInspectionStatus shipmentInspectionStatus;
    private long inspectorId;
    private LocalDateTime startTime;
    private LocalDateTime lastUpdatedTime;
    private LocalDateTime endTime;
    private String remarks; // Any additional comments
    //****************************************************************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public ShipmentInspectionStatus getInspectionStatus() {
        return shipmentInspectionStatus;
    }

    public void setInspectionStatus(ShipmentInspectionStatus shipmentInspectionStatus) {
        this.shipmentInspectionStatus = shipmentInspectionStatus;
    }

    public long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(long inspectorId) {
        this.inspectorId = inspectorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
