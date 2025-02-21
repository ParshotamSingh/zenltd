package com.zenltd.entity;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Table(name = "container")
public class Container{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "container_code")
    private long containerCode;
    @Column (name = "shipment_id")
    private long shipmentId;
    //****************************************


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(long containerCode) {
        this.containerCode = containerCode;
    }


    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
