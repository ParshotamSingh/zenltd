package com.zenltd.dto;

import com.zenltd.entity.Shipment;

import javax.persistence.Column;
import java.time.Instant;

public class ShipmentArrivalDto {
    private  long shipmentArrivalId;
//    private Shipment shipment;
    private long shipmentId;
    private String gateNumber;
    private String shipmentStatus;
    private Long updatedByEmployeeId;
    private Instant dateCreated;
    //**********************************************

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getShipmentArrivalId() {
        return shipmentArrivalId;
    }

    public void setShipmentArrivalId(long shipmentArrivalId) {
        this.shipmentArrivalId = shipmentArrivalId;
    }

//    public Shipment getShipment() {
//        return shipment;
//    }
//
//    public void setShipment(Shipment shipment) {
//        this.shipment = shipment;
//    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Long getUpdatedByEmployeeId() {
        return updatedByEmployeeId;
    }

    public void setUpdatedByEmployeeId(Long updatedByEmployeeId) {
        this.updatedByEmployeeId = updatedByEmployeeId;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }
}
