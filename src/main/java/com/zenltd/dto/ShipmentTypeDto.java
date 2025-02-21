package com.zenltd.dto;

import javax.persistence.Column;

public class ShipmentTypeDto{
private long id;
public String shipmentType;
private String shipmentTypeCode;
private String shipmentTypeName;
//********************************************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getShipmentTypeCode() {
        return shipmentTypeCode;
    }

    public void setShipmentTypeCode(String shipmentTypeCode) {
        this.shipmentTypeCode = shipmentTypeCode;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }
}
