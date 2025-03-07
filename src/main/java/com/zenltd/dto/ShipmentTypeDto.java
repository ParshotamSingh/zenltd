package com.zenltd.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ShipmentTypeDto {
    private long id;
    public String shipmentType;
    private String shipmentTypeCode;
    private String shipmentTypeName;
}