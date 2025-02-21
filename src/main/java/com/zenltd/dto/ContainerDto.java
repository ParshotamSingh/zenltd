package com.zenltd.dto;

import java.util.List;

public class ContainerDto {
    private long id;
    private long containerCode;
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
