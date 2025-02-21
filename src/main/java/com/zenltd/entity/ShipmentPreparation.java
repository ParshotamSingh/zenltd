package com.zenltd.entity;

import com.zenltd.enums.ShipmentPreparationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipment_preparation")
public class ShipmentPreparation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long Id;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "gate_number")
    private String gateNumber;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "status")
    private ShipmentPreparationStatus status; // "In Progress", "Completed", "Delayed", etc.
    @Column(name = "remarks")
    private String remarks; // Any additional comments
    //****************************************************


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
