package com.zenltd.entity;

import com.zenltd.enums.ShipmentInboundStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_allocation")
public class EmployeeAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "emp_id")
    private long empId;
    @Column(name = "shipment_status")
    private ShipmentInboundStatus shipmentStatus;
    @Column(name = "createdBy")
    private long createdBy;
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
    //**********************************

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

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public ShipmentInboundStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentInboundStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}

