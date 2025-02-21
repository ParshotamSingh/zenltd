package com.zenltd.entity;

import com.zenltd.enums.ShipmentInspectionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipment_inspection")
public class ShipmentInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "inspection_status")
    private ShipmentInspectionStatus shipmentInspectionStatus;
    @Column(name = "inspector_id")
    private long inspectorId;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "remarks")
    private String remarks; // Any additional comments
    //****************************************************

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
