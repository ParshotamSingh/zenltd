package com.zenltd.entity;

import javax.persistence.*;
@Entity
public class ShipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shipment_type")
    public String shipmentType;
    @Column(name = "shipment_type_code")
    private String shipmentTypeCode;
    @Column(name = "shipment_type_name")
    private String shipmentTypeName;
    //****************************


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
