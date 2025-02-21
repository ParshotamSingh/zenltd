package com.zenltd.dto;

import com.zenltd.enums.ProductInboundStatus;
import com.zenltd.enums.ProductInspectionStatus;

import java.time.LocalDateTime;

public class InboundReceivingDto {
    private long id;
    private long productCode;
    private long shipmentId;
    private long inspectorId;
    private ProductInspectionStatus productInspectionStatus;
    private ProductInboundStatus productInboundStatus;
    private LocalDateTime scannedTime;
    private LocalDateTime inspectedTime;
    //**********************************************************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(long inspectorId) {
        this.inspectorId = inspectorId;
    }

    public ProductInspectionStatus getProductInspectionStatus() {
        return productInspectionStatus;
    }

    public void setProductInspectionStatus(ProductInspectionStatus productInspectionStatus) {
        this.productInspectionStatus = productInspectionStatus;
    }

    public ProductInboundStatus getProductInboundStatus() {
        return productInboundStatus;
    }

    public void setProductInboundStatus(ProductInboundStatus productInboundStatus) {
        this.productInboundStatus = productInboundStatus;
    }

    public LocalDateTime getScannedTime() {
        return scannedTime;
    }

    public void setScannedTime(LocalDateTime scannedTime) {
        this.scannedTime = scannedTime;
    }

    public LocalDateTime getInspectedTime() {
        return inspectedTime;
    }

    public void setInspectedTime(LocalDateTime inspectedTime) {
        this.inspectedTime = inspectedTime;
    }
}
