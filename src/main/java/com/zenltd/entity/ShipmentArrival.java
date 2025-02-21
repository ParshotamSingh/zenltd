package com.zenltd.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "shipment_arrival")
public class ShipmentArrival {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long shipmentArrivalId;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "gate_number")
    private String gateNumber;
    @Column(name = "shipment_status")
    private String shipmentStatus;
    @Column(name = "updated_by_employee_id")
    private Long updatedByEmployeeId;
    @Column(name = "date_created")
    private Instant dateCreated;
    //*******************************


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
