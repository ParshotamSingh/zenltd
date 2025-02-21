package com.zenltd.dto;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDto {
    private  long shipmentId;
    private long memberId;
    private String shipmentBarcodeId;
    private String businessUnitId;
    private String referenceNumber;
    private String transportType;
    private long shipmentTypeId;
    private String source;
    private String createdByUsername;
    private String userUpdated;
    private Instant dateCreated;
    private Instant dateUpdated;
    private Instant expectedDeliveryDate;

    //*******************************************************

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }


    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentBarcodeId() {
        return shipmentBarcodeId;
    }

    public void setShipmentBarcodeId(String shipmentBarcodeId) {
        this.shipmentBarcodeId = shipmentBarcodeId;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public long getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(long shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public String getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Instant getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Instant expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
